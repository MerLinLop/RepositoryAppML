package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Seller : Serializable {
    var id: Int? = null
    var permalink: String? = null
    var registration_date: String? = null
    var car_dealer: Boolean? = null
    var real_estate_agency: Boolean? = null
    var tags: List<String>? = null
    var car_dealer_logo: String? = null
    var home_image_url: String? = null
    var eshop: Eshop? = null
    var sellerReputation: SellerReputation? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
