package se.daresay.car_service.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.PlaceListMapTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class EmptyScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val row = Row.Builder()
            .setTitle("Empty Screen")
            .build()
        val pane = Pane.Builder()
            .addRow(row)
            .build()
        return PaneTemplate.Builder(pane)
            .setTitle("Empty")
            .build()
    }
}