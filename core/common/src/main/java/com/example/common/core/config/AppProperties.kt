package com.example.common.core.config

import android.content.Context
import com.example.common.R
import java.util.*

@Suppress("UNCHECKED_CAST")
class AppProperties(context: Context) {

    private val properties: Map<String, String> = initProperties(context)

    /* инициализация пропертей из config файла в map */
    private fun initProperties(context: Context): Map<String, String> {
        return context.resources.openRawResource(R.raw.config).use {
            val properties = Properties()
            properties.load(it)
            properties as Map<String, String>
        }
    }

    /* Получение настроек сети из config файла по ключам */
    fun networkProperties(): NetworkProperties {
        val connectTimeout = properties["http.timeout.connect"]?.toLong() ?: 0
        val readTimeout = properties["http.timeout.read"]?.toLong() ?: 0
        val writeTimeout = properties["http.timeout.write"]?.toLong() ?: 0
        return NetworkProperties(connectTimeout, readTimeout, writeTimeout)
    }

    /* ПОлучения базового юрл */
    fun getBaseUrl(): String = properties["base_url"] ?: ""
}