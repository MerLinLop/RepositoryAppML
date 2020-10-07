package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Eshop : Serializable {
    var nickName: String? = null
    var eshopRubro: EshopRubro? = null
    var eshopId: Int? = null
    var eshopLocations: List<EshopLocation>? = null
    var siteId: String? = null
    var eshopLogoUrl: String? = null
    var eshopStatusId: Int? = null
    var seller: Int? = null
    var eshopExperience: Int? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
