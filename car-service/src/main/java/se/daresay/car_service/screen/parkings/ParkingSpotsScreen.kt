package se.daresay.car_service.screen.parkings

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
import se.daresay.car_service.screen.details.DetailsScreen
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

    private fun createGridItem(item: ParkingSpot) = GridItem.Builder()
            .setTitle(item.name)
            .setText(item.description)
            .setOnClickListener {
                screenManager.push(DetailsScreen(carContext, item.id))
            }
            .setImage(
                CarIcon.Builder(
                    IconCompat.createWithResource(carContext,
                        if(item.isActive)
                            R.drawable.icon_check
                        else
                            R.drawable.icon_block
                    )
                ).build(),
                GridItem.IMAGE_TYPE_ICON
            ).build()


}
