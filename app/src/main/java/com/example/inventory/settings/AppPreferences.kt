package com.example.inventory.settings

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AppPreferences(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "app_settings",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var hideSensitiveData: Boolean
        get() = prefs.getBoolean("hide_sensitive_data", false)
        set(value) = prefs.edit { putBoolean("hide_sensitive_data", value) }

    var disableShare: Boolean
        get() = prefs.getBoolean("disable_share", false)
        set(value) = prefs.edit { putBoolean("disable_share", value) }

    var useDefaultQuantity: Boolean
        get() = prefs.getBoolean("use_default_quantity", false)
        set(value) = prefs.edit { putBoolean("use_default_quantity", value) }

    var defaultQuantity: Int
        get() = prefs.getInt("default_quantity", 1)
        set(value) = prefs.edit { putInt("default_quantity", value) }
}