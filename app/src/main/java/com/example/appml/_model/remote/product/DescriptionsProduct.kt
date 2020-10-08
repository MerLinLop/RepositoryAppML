package com.example.appml._model.remote.product

import java.util.*


class DescriptionsProduct {
    var id: String? = null
    var created: String? = null
    var text: String? = null
    var plain_text: String? = null
    var snapshot: Snapshot? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
