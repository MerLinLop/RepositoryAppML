package com.example.appml._model.remote.product

import java.util.*


class Pictures {
    var id: String? = null
    var url: String? = null
    var secure_url: String? = null
    var size: String? = null
    var max_size: String? = null
    var quality: String? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
