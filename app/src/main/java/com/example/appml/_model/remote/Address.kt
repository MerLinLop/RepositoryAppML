package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Address: Serializable {
    var state_id: String? = null
    var state_name: String? = null
    var city_id: String? = null
    var city_name: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
