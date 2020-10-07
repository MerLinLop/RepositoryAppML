package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Shipping : Serializable {
    var free_shipping: Boolean? = null
    var mode: String? = null
    var tags: List<String>? = null
    var logistic_type: String? = null
    var store_pick_up: Boolean? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
