package com.example.appml._model.remote

import java.util.*

class Search {
    var site_id: String? = null
    var query: String? = null
    var paging: Paging? = null
    var results: List<Results>? = null
    var secondaryResults: List<Any>? = null
    var relatedResults: List<Any>? = null
    var sort: AdressBasicObject? = null
    var availableSorts: List<AdressBasicObject>? = null
    var filters: List<Any>? = null
    var availableFilters: List<AvailableFilter>? = null
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}
