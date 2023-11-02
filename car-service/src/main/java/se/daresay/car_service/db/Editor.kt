package se.daresay.car_service.db

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREF = "KnightecCarPark"
fun Context.save(key: String, value: String) {
    val editor: SharedPreferences.Editor = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit()
    editor.putString(key, value)
    editor.apply()
}

fun Context.load(key: String): String? =
    this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).getString(key, null)