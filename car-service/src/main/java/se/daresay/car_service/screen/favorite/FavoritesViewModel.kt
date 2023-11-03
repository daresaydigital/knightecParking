package se.daresay.car_service.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import se.daresay.car_service.screen.details.State
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.usecase.GetAllFavoriteSpots

class FavoritesViewModel(private val getAllFavoriteSpots: GetAllFavoriteSpots): ViewModel() {

    private val _spots: MutableStateFlow<State<List<ParkingSpot>>> = MutableStateFlow(State.Idle());
    val spots = _spots.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoriteSpots().collect{
                _spots.value = State.Data(it)
            }
        }
    }

}
