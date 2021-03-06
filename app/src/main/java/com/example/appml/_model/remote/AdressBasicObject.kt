package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class AdressBasicObject : Serializable {
    var id: String? = null
    var name: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
