package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Attribute : Serializable {
    var name: String? = null
    var value_struct: ValueStruct? = null
    var values: List<Value>? = null
    var attribute_group_id: String? = null
    var attribute_group_name: String? = null
    var id: String? = null
    var value_id: String? = null
    var value_name: String? = null
    var source: Long? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
