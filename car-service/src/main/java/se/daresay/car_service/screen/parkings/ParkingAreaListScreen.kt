package se.daresay.car_service.screen.parkings

import android.util.Log
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
import se.daresay.car_service.screen.login.SignInUserNameScreen
import se.daresay.domain.base.Response
import se.daresay.domain.model.Area

class ParkingAreaListScreen(carContext: CarContext) : BaseScreen(carContext) {

    private var viewModel : ParkingViewModel
    private var listBuilder = ItemList.Builder()
    private var chosenArea = Area.UNKNOWN
    init {
        viewModel = get(ParkingViewModel::class.java)
        area()
        parkingSpots()
    }

    private fun area(){
        lifecycleScope.launch {
            viewModel.area.collect{
                when(it){
                    is Response.Idle -> {
                        Log.d("HERE","I'm HERE")
                        viewModel.getAreas()
                    }
                    is Response.Error -> {
                        Log.d("HERE",it.exception.message.toString())
                    }
                    is Response.Data -> {
                        Log.d("HERE","I'm HERE with data")

                        it.data.forEach { area ->
                            listBuilder.addItem(
                                Row.Builder()
                                    .setOnClickListener {
                                        chosenArea = Area.valueOf(area.uppercase())
                                        viewModel.getParkingSpots()
                                    }
                                    .setTitle(area)
                                    .build()
                            )
                        }

                        invalidate()
                    }
                    is Response.Loading -> {
                        // TODO needed ?
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
                        Log.d("HERE",it.exception.message.toString())
                    }
                    is Response.Data -> {
                        screenManager.push(ParkingSpotsScreen(
                            carContext,
                            it.data.filter {
                                it.area == chosenArea
                            }
                        ))
                    }
                    is Response.Loading -> {
                        Log.d("HERE",it.msg)
                    }
                }
            }
        }
    }

    override fun onGetTemplate(): Template {

        return ListTemplate.Builder()
            .setSingleList(listBuilder.build())
            .setTitle("Office List")
            .setHeaderAction(Action.APP_ICON)
            .build()
    }
}