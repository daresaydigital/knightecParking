package se.daresay.car_service.db

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import kotlinx.coroutines.flow.Flow

private const val SHARED_PREF = "KnightecCarPark"


class Editor (context: Context) {
    private val flowSharedPreferences : FlowSharedPreferences
    private val sharedPreferences : SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        flowSharedPreferences = FlowSharedPreferences(sharedPreferences)
    }

    fun save(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun load(key: String): Flow<String?> =
        flowSharedPreferences.getNullableString(key, null).asFlow()

    fun loadValue(key: String): String? =
        sharedPreferences.getString(key, null)

}
