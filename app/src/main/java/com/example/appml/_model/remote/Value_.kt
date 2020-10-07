package com.example.appml._model.remote

import java.util.*


class Value_ {
    var id: String? = null
    var name: String? = null
    var results: Int? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
