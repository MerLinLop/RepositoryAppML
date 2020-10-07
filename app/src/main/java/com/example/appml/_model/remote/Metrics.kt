package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Metrics : Serializable {
    var claims: MetricsBasic? = null
    var delayedHandlingTime: MetricsBasic? = null
    var sales: Sales? = null
    var cancellations: MetricsBasic? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
