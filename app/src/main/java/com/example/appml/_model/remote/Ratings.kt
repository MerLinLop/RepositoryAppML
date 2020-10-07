package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Ratings : Serializable {
    var negative: Double? = null
    var positive: Double? = null
    var neutral: Double? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
