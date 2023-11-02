package se.daresay.car_service.screen.parkings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import se.daresay.domain.base.Response
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.usecase.GetAllParking

class ParkingSpotsViewModel(private val getAllParking: GetAllParking): ViewModel()  {
    private val _parkings: MutableStateFlow<Response<List<ParkingSpot>>> = MutableStateFlow(Response.Idle())
    val parkings = _parkings.asStateFlow()

    fun getParkingSpots(){
        viewModelScope.launch {
            getAllParking.invoke().collect{
                _parkings.value = it
            }
        }
    }
}