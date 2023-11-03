package se.daresay.car_service.screen.parkings

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.R
import se.daresay.car_service.screen.BaseScreen
import se.daresay.car_service.screen.login.SignInUserNameScreen
import se.daresay.domain.base.Response
import se.daresay.domain.model.Area

class ParkingAreaListScreen(carContext: CarContext) : BaseScreen(carContext) {

    private var viewModel : ParkingViewModel = get(ParkingViewModel::class.java)
    private var listBuilder = ItemList.Builder()
    private var chosenArea = Area.UNKNOWN
    private var loading = true


    override fun onCreate(owner: LifecycleOwner) {
        area()
        parkingSpots()
    }

    private fun area(){
        lifecycleScope.launch {
            viewModel.area.collect{
                when(it){
                    is Response.Idle -> {
                        viewModel.getAreas()
                    }
                    is Response.Error -> {
                        loading = false
                    }
                    is Response.Data -> {
                        loading = false
                        it.data.forEach { area ->
                            listBuilder.addItem(
                                Row.Builder()
                                    .setOnClickListener {
                                        invalidate()
                                        chosenArea = Area.valueOf(area.uppercase())
                                        viewModel.getParkingSpots()
                                    }
                                    .setImage(
                                        CarIcon.Builder(
                                            IconCompat.createWithResource(carContext,
                                                when(area.uppercase()){
                                                    Area.KISTA.name -> R.drawable.icon_daresay
                                                    Area.SOLNA.name -> R.drawable.icon_knightec
                                                    else -> R.drawable.icon_unknown
                                                }
                                            )
                                        ).build()
                                    )
                                    .setTitle(area)
                                    .build()
                            )
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
                            it.data.filter {
                                it.area == chosenArea
                            }
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
            .setLoading(loading)
        if (loading)
            listTemplate.setLoading(true)
        else
            listTemplate.setSingleList(listBuilder.build())
        return listTemplate.build()
    }
}