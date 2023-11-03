package se.daresay.car_service.screen.details

import android.graphics.Color
import androidx.car.app.CarContext
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarColor
import androidx.car.app.model.CarIcon
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import se.daresay.car_service.R
import se.daresay.car_service.screen.BaseScreen
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.model.toIntent

class DetailsScreen(carContext: CarContext, private val placeId: Int) : BaseScreen(carContext) {
    private var spotDetails: ParkingSpot? = null

    private var viewModel: DetailsViewModel = get(DetailsViewModel::class.java)


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        configViewModel()
        viewModel.loadDetails(placeId)
    }

    override fun onGetTemplate(): Template {
        val spotDetails = if (spotDetails == null) {
            return MessageTemplate.Builder("Loading...").setLoading(true).build()
        } else {
            spotDetails!!
        }

        val navigateAction = Action.Builder()
            .setTitle("Navigate")
            .setIcon(
                CarIcon.Builder(
                    IconCompat.createWithResource(
                        carContext,
                        R.drawable.baseline_navigation_24
                    )
                ).build()
            )
            .setOnClickListener { spotDetails.let { carContext.startCarApp(it.toIntent(CarContext.ACTION_NAVIGATE)) } }
            .build()

        val actionStrip = ActionStrip.Builder()
            .addAction(
                Action.Builder()
                    .setIcon(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                R.drawable.baseline_favorite_24
                            )
                        ).setTint(
                            if (spotDetails.isFavorite) CarColor.RED else CarColor.createCustom(
                                Color.LTGRAY,
                                Color.DKGRAY
                            )
                        ).build()
                    )
                    .setOnClickListener {
                        viewModel.onFavoritePress(spotDetails.copy(isFavorite = !spotDetails.isFavorite))
                    }.build()
            )
            .build()

        return PaneTemplate.Builder(
            Pane.Builder()
                .addAction(navigateAction)
                .addRow(
                    Row.Builder()
                        .setTitle("Coordinates")
                        .addText("${spotDetails.latitude}, ${spotDetails.longitude}")
                        .build()
                ).addRow(
                    Row.Builder()
                        .setTitle("Description")
                        .addText(spotDetails.description)
                        .build()
                ).build()
        )
            .setTitle(spotDetails.name)
            .setHeaderAction(Action.BACK)
            .setActionStrip(actionStrip)
            .build()
    }

    private fun configViewModel() {
        lifecycleScope.launch {
            viewModel.spot.collect {
                when (it) {
                    is State.Data -> {
                        spotDetails = it.data
                        invalidate()
                    }
                    else -> {}
                }
            }
        }
    }
}
