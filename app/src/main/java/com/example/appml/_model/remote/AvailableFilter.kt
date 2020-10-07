package com.example.appml._model.remote

import java.util.*


class AvailableFilter {
    var id: String? = null
    var name: String? = null
    var type: String? = null
    var values: List<Value_>? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
