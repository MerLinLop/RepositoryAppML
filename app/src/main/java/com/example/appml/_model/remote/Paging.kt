package com.example.appml._model.remote

import java.util.*


class Paging {
    var total: Int? = null
    var primary_results: Int? = null
    var offset: Int? = null
    var limit: Int? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
