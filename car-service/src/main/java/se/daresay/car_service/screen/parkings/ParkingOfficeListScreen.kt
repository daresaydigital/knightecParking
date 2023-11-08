package se.daresay.car_service.screen.parkings

import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarColor
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.R
import se.daresay.car_service.screen.BaseScreen
import se.daresay.car_service.screen.favorite.FavoritesScreen
import se.daresay.domain.base.Response
import se.daresay.domain.model.Area

class ParkingOfficeListScreen(carContext: CarContext) : BaseScreen(carContext) {

    private var viewModel : ParkingViewModel = get(ParkingViewModel::class.java)
    private var listBuilder = ItemList.Builder()
    private var loading = true

    override fun onCreate(owner: LifecycleOwner) {
        office()
        parkingSpots()
    }

    private fun office(){
        lifecycleScope.launch {
            viewModel.office.collect{
                when(it){
                    is Response.Idle -> {
                        viewModel.getAreas()
                    }
                    is Response.Error -> {
                        loading = false
                    }
                    is Response.Data -> {
                        loading = false

                        it.data.forEach { office ->

                            val row = Row.Builder()
                                .setOnClickListener {
                                    invalidate()
                                    viewModel.chosenArea = Area.valueOf(office.name.uppercase())
                                    viewModel.getParkingSpots()
                                }
                                .setTitle(office.name)
                            row.addText(office.address)
                            office.phoneNumber?.let {
                                row.addText(it)
                            }
                            row.setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(carContext,
                                        when(office.name.uppercase()){
                                            Area.KISTA.name -> R.drawable.icon_daresay
                                            Area.SOLNA.name -> R.drawable.icon_knightec
                                            else -> R.drawable.icon_unknown
                                        }
                                    )
                                ).build()
                            )
                            listBuilder.addItem(row.build())
                        }

                        invalidate()
                    }
                    is Response.Loading -> {
                        loading = true
                    }
                }
            }

        }
    }

    private fun prepareFavorites(): ActionStrip {
        return ActionStrip.Builder()
            .addAction(
                Action.Builder()
                    .setIcon(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                R.drawable.baseline_favorite_24
                            )
                        ).setTint(
                            CarColor.RED
                        )
                            .build()
                    )
                    .setTitle("Favorites")
                    .setOnClickListener {
                        screenManager.push(FavoritesScreen(carContext))
                    }.build()
            )
            .build()
    }

    private fun parkingSpots() {
        lifecycleScope.launch {
            viewModel.parkings.collect{
                when(it){
                    is Response.Idle -> {}
                    is Response.Error -> {
                        // TODO error ?
                    }
                    is Response.Data -> {
                        loading = false
                        screenManager.push(ParkingSpotsScreen(
                            carContext,
                            it.data
                        ))
                    }
                    is Response.Loading -> {
                        loading = true
                    }
                }
            }
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        viewModel.reset()
    }

    override fun onGetTemplate(): Template {
        val listTemplate = ListTemplate.Builder()
            .setTitle("Office List")
            .setHeaderAction(Action.APP_ICON)
        if (loading)
            listTemplate.setLoading(true)
        else
            listTemplate.setSingleList(listBuilder.build())
        return listTemplate.build()
    }
}
