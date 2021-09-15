package com.snapnoob.tugas11.preference

import android.content.Context
import android.content.SharedPreferences

interface RegisteredUserPreference {
    fun setUserName(name: String)
    fun getUserName(): String

    fun setUserEmail(email: String)
    fun getUserEmail(): String

    fun setUserPassword(password: String)
    fun getUserPassword(): String

    fun setUserBloodType(bloodType: String)
    fun getUserBloodType(): String
}

class RegisteredUserPreferenceImpl(
    private val context: Context
) : RegisteredUserPreference {

    private fun getSharedPreference(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun setUserName(name: String) {
        getSharedPreference().edit().putString(USER_NAME, name).apply()
    }

    override fun getUserName(): String =
        getSharedPreference().getString(USER_NAME, "")!!

    override fun setUserEmail(email: String) {
        getSharedPreference().edit().putString(USER_EMAIL, email).apply()
    }

    override fun getUserEmail(): String =
        getSharedPreference().getString(USER_EMAIL, "")!!

    override fun setUserPassword(password: String) {
        getSharedPreference().edit().putString(USER_PASSWORD, password).apply()
    }

    override fun getUserPassword(): String =
        getSharedPreference().getString(USER_PASSWORD, "")!!

    override fun setUserBloodType(bloodType: String) {
        getSharedPreference().edit().putString(USER_BLOOD_TYPE, bloodType).apply()
    }

    override fun getUserBloodType(): String =
        getSharedPreference().getString(USER_BLOOD_TYPE, "")!!

    companion object {
        private const val SHARED_PREF_NAME = "registered_user_preference"
        private const val USER_NAME = "user_name"
        private const val USER_EMAIL = "user_email"
        private const val USER_PASSWORD = "user_password"
        private const val USER_BLOOD_TYPE = "user_blood_type"
    }
}