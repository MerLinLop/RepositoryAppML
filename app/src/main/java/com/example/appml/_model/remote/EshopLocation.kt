package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class EshopLocation : Serializable {
    var state: IdBasicObject? = null
    var neighborhood: IdBasicObject? = null
    var country: IdBasicObject? = null
    var city: IdBasicObject? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
