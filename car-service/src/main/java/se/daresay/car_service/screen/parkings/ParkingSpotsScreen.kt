package se.daresay.car_service.screen.parkings

import android.graphics.BitmapFactory
import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.ItemList
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import se.daresay.car_service.R
import se.daresay.car_service.screen.BaseScreen
import se.daresay.domain.model.Area
import se.daresay.domain.model.ParkingSpot

class ParkingSpotsScreen(
    carContext: CarContext,
    private val parkingSpots: List<ParkingSpot>
) : BaseScreen(carContext) {



    override fun onGetTemplate(): Template {
        val gridItemListBuilder = ItemList.Builder()
        parkingSpots.forEach {
            gridItemListBuilder.addItem(createGridItem(it))
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
        val resources = carContext.resources
        val bitmap = when(type) {
            Area.KISTA -> BitmapFactory.decodeResource(resources, R.drawable.daresay)
            Area.SOLNA -> BitmapFactory.decodeResource(resources, R.drawable.knightec)
            Area.UNKNOWN -> BitmapFactory.decodeResource(resources, R.drawable.knightec)
        }
        val mIcon = IconCompat.createWithBitmap(bitmap)
        return mIcon
    }


}