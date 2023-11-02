package se.daresay.car_service.screen.parkings

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.ItemList
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.R
import se.daresay.domain.base.Response
import se.daresay.domain.model.Area
import se.daresay.domain.model.ParkingSpot

class ParkingSpotsScreen(carContext: CarContext) : Screen(carContext) {

    private lateinit var viewModel : ParkingSpotsViewModel
    val gridItemListBuilder = ItemList.Builder()

    override fun onGetTemplate(): Template {
        viewModel = get(ParkingSpotsViewModel::class.java)

        lifecycleScope.launch {
            viewModel.parkings.collect{
                when(it){
                    is Response.Idle -> {
                        viewModel.getParkingSpots()
                    }
                    is Response.Error -> {
                        Log.d("HERE",it.exception.message.toString())
                    }
                    is Response.Data -> {
                        it.data.forEach {
                            gridItemListBuilder.addItem(createGridItem(it))
                        }
                        invalidate()
                    }
                    is Response.Loading -> {
                        Log.d("HERE",it.msg)
                    }
                }
            }
        }


        return GridTemplate.Builder()
            .setHeaderAction(Action.APP_ICON)
            .setSingleList(gridItemListBuilder.build())
            .setTitle("Parking Spots")
            .setHeaderAction(Action.BACK)
            .build()
    }

    private fun createGridItem(item: ParkingSpot) =
        GridItem.Builder()
            .setImage(
                CarIcon.Builder(getIcon(item.area)).build(),
                GridItem.IMAGE_TYPE_ICON
            )
            .setTitle(item.name)
            .setText(item.description)
            .setOnClickListener {
                // TODO navigate to next screen
            }
            .build()

    private fun getIcon(type: Area): IconCompat {
        val mIcon = IconCompat.createWithResource(carContext,
            when(type) {
                Area.KISTA -> R.drawable.daresay
                Area.SOLNA -> R.drawable.knightec
                Area.UNKNOWN -> R.drawable.unknown
            }
        )
        return mIcon
    }


}