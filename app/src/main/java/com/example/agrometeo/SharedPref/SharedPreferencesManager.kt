package com.example.agrometeo.SharedPref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    //like static class
    companion object{
        const val PREF_NAME_AGRO_METEO = "shared_prefernces_agro_meteo"
        val EMAIL_SP = "email"
        val  REMEMBER_ME_SP = "remember"
        val IS_LOGGED_IN_SP = "logged"
    }

    fun saveUserEmail(email: String ){
        sharedPrefs.edit()
            .putString(EMAIL_SP, email)
            .apply()
    }

    fun saveRemmeberMe(remember: Boolean ){
        sharedPrefs.edit()
            .putBoolean(REMEMBER_ME_SP,remember)
            .apply()
    }

    fun saveLoggedIn(logged: Boolean ){
        sharedPrefs.edit()
            .putBoolean(IS_LOGGED_IN_SP,logged)
            .apply()
    }

    fun isLoggedIn(): Boolean{
        return sharedPrefs.getBoolean(IS_LOGGED_IN_SP,false)
    }
    fun clear (){
        sharedPrefs.edit().clear().apply()
    }

    fun getEmail(): String{
        return  sharedPrefs.getString(EMAIL_SP,"")?: ""
    }

    fun isRememberMe (): Boolean{
        return sharedPrefs.getBoolean(REMEMBER_ME_SP,false)
    }


    //create the sharedPreferences
    private val sharedPrefs : SharedPreferences = context.getSharedPreferences(com.example.agrometeo.SharedPref.SharedPreferencesManager.Companion.PREF_NAME_AGRO_METEO, Context.MODE_PRIVATE)

}