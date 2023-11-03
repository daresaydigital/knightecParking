package se.daresay.car_service.screen.favorite

import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.screen.BaseScreen
import se.daresay.car_service.screen.details.DetailsScreen
import se.daresay.car_service.screen.details.State
import se.daresay.domain.model.ParkingSpot

class FavoritesScreen(private val carContext: CarContext) : BaseScreen(carContext) {

    private var viewModel: FavoritesViewModel = get(FavoritesViewModel::class.java)

    private var spots: List<ParkingSpot> = emptyList();

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        lifecycleScope.launch {
            viewModel.spots.collect {
                if (it is State.Data) {
                    spots = it.data
                    invalidate()
                }
            }
        }
    }

    override fun onGetTemplate(): Template {
        val itemList = ItemList.Builder()

        spots.forEach { spot ->
            itemList.addItem(Row.Builder().setTitle(spot.name).addText(spot.description).setOnClickListener {
                screenManager.push(DetailsScreen(carContext, spot.id))
            }.build())
        }

        itemList
            .setNoItemsMessage("No favorites")

        return ListTemplate.Builder()
            .setHeaderAction(Action.BACK)
            .setTitle("Favorites")
            .setSingleList(itemList.build())
            .build()
    }
}
