package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class SellerAddress : Serializable {
    var id: String? = null
    var comment: String? = null
    var address_line: String? = null
    var zip_code: String? = null
    var country: AdressBasicObject? = null
    var state: AdressBasicObject? = null
    var city: AdressBasicObject? = null
    var latitude: String? = null
    var longitude: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}

