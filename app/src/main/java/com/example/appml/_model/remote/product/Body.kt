package com.example.appml._model.remote.product

import com.example.appml._model.remote.Attribute
import java.util.*

class Body {
    var pictures: List<Pictures>? = null
    var attributes: List<Attribute>? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}