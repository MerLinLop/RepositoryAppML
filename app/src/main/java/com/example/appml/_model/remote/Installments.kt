package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Installments: Serializable {
    var quantity: Int? = null
    var amount: Double? = null
    var rate: Double? = null
    var currencyId: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
