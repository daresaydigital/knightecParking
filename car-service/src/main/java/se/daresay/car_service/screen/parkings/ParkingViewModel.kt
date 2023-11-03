package se.daresay.car_service.screen.parkings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import se.daresay.domain.base.Response
import se.daresay.domain.model.Area
import se.daresay.domain.model.Office
import se.daresay.domain.model.ParkingSpot
import se.daresay.domain.usecase.GetAllOffices
import se.daresay.domain.usecase.GetAllParking

class ParkingViewModel(
    private val getAllParking: GetAllParking,
    private val getAllOffice: GetAllOffices
): ViewModel()  {
    private val _parkings: MutableStateFlow<Response<List<ParkingSpot>>> = MutableStateFlow(Response.Idle())
    val parkings = _parkings.asStateFlow()

    private val _office: MutableStateFlow<Response<List<Office>>> = MutableStateFlow(Response.Idle())
    val office = _office.asStateFlow()
    var chosenArea: Area? = null
    fun getParkingSpots() {
        viewModelScope.launch {
            getAllParking.invoke().collect{
                when(it){
                    is Response.Data -> {
                        _parkings.value = it.copy(
                            data = it.data.filter {
                                it.area == chosenArea
                            }
                        )
                    }
                    else -> _parkings.value = it
                }

            }
        }
    }

    fun getAreas() {
        viewModelScope.launch {
            getAllOffice.invoke().collect{
                _office.value = it
            }
        }
    }

    fun reset() {
        viewModelScope.launch {
            _parkings.value = Response.Idle()
        }
    }

}