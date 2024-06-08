package com.reading.novel.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = appContext.getSharedPreferences("MeterPreferenceFile", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    fun getString(key: String, default: String? = null): String? {
        return preference.getString(key, default)
    }

    fun saveInt(key: String, value: Int) {
        preference.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defalultVal: Int = 0): Int {
        return preference.getInt(key, defalultVal)
    }

    fun saveLong(key: String, value: Long) {
        preference.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defalultVal: Long = 0L): Long {
        return preference.getLong(key, defalultVal)
    }

    fun saveBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return preference.getBoolean(key, defaultValue)
    }

    fun remove(key: String) {
        preference.edit().remove(key).apply()
    }

    fun removeAll() {
        preference.edit().clear().apply()
    }

    companion object {

        const val KEY_COOKIE = "cookie"

        const val ACTIVE_CONTRACT_ID = "active_contract_id"
        const val ACTIVE_METER_TYPE = "active_meter_type"
        const val ACTIVE_USER_EMAIL = "active_user_email"

        //nickname preferences
        const val NICK_NAME = "nick_name"

        //notification preferences
        const val LAST_BILL_IS_AVAILABLE = "last_bill_is_available"
        const val BILL_IS_DUE_SOON = "bill_is_due_soon"
        const val TIME_TO_READ_MY_METER = "time_to_read_my_meter"

        //sync preferences
        const val LAST_DAY_SYNCED_AT = "last_day_synced_at"

        //last user interaction preferences
        const val LAST_USER_INTERACTION = "last_user_interaction"

        //
        const val ACTIVE_CONTRACT_PROFILE_ID = "active_contract_profile_id"

        //New User
        const val IS_NEW_USER = "is_new_user"
        const val MFA_LAST_APPEARANCE = "mfa_last_appearance"

    }

}