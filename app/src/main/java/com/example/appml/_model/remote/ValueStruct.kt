package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class ValueStruct : Serializable {
    var number: Double? = null
    var unit: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}