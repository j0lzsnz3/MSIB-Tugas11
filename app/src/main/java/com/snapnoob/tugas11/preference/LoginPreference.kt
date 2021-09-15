package com.snapnoob.tugas11.preference

import android.content.Context
import android.content.SharedPreferences

interface LoginPreference {
    fun setIsAlreadyLogin(isLogin: Boolean)
    fun getIsAlreadyLogin(): Boolean

    fun setSavedEmailName(email: String)
    fun getSavedEmailName(): String
}

class LoginPreferenceImpl(
    private val context: Context
) : LoginPreference {

    private fun getSharedPreference(): SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun setIsAlreadyLogin(isLogin: Boolean) {
        getSharedPreference().edit().putBoolean(IS_ALREADY_LOGIN, isLogin).apply()
    }

    override fun getIsAlreadyLogin(): Boolean =
        getSharedPreference().getBoolean(IS_ALREADY_LOGIN, false)

    override fun setSavedEmailName(email: String) {
        getSharedPreference().edit().putString(SAVED_EMAIL_NAME, email).apply()
    }

    override fun getSavedEmailName(): String =
        getSharedPreference().getString(SAVED_EMAIL_NAME, "")!!

    companion object {
        private const val SHARED_PREF_NAME = "login_state_preference"
        private const val IS_ALREADY_LOGIN = "is_already_login"
        private const val SAVED_EMAIL_NAME = "saved_email_name"
    }
}