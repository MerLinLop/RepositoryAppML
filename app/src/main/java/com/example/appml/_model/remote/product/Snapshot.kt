package com.example.appml._model.remote.product

import java.util.*


class Snapshot {
    var url: String? = null
    var width: Int? = null
    var height: Int? = null
    var status: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
