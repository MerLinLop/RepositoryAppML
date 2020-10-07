package com.example.appml._model.remote

import java.io.Serializable
import java.util.*


class Results : Serializable{
    var id: String? = null
    var site_id: String? = null
    var title: String? = null
    var seller: Seller? = null
    var price: Double? = null
    var prices: Prices? = null
    var sale_price: Any? = null
    var currency_id: String? = null
    var available_quantity: Double? = null
    var sold_quantity: Double? = null
    var buying_mode: String? = null
    var listing_type_id: String? = null
    var stop_time: String? = null
    var condition: String? = null
    var permalink: String? = null
    var thumbnail: String? = null
    var accepts_mercadopago: Boolean? = null
    var installments: Installments? = null
    var address: Address? = null
    var shipping: Shipping? = null
    var seller_address: SellerAddress? = null
    var attributes: List<Attribute>? = null
    var differential_pricing: DifferentialPricing? = null
    var original_price: Double? = null
    var category_id: String? = null
    var official_store_id: Long? = null
    var domain_id: String? = null
    var catalog_product_id: String? = null
    var tags: List<String>? = null
    var order_backend: Long? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
