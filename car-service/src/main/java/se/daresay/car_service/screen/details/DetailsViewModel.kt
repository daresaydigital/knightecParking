package se.daresay.car_service.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.usecase.GetSpotDetails
import se.daresay.domain.usecase.SetFavorite

class DetailsViewModel(
    private val getSpotDetails: GetSpotDetails,
    private val setFavorite: SetFavorite
) : ViewModel() {

    private val _spot: MutableStateFlow<State<ParkingSpot>> = MutableStateFlow(State.Idle());
    val spot = _spot.asStateFlow()

    fun loadDetails(spotId: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            getSpotDetails(spotId).collect { details ->
                details?.let {
                    _spot.value = State.Data(it)
                }
            }
        }
    }

    fun onFavoritePress(isFavorite: ParkingSpot) {
        viewModelScope.launch(Dispatchers.IO) {
            setFavorite(isFavorite)
        }
    }
}
