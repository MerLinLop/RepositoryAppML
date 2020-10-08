package com.example.appml._model.networking

import com.example.appml._model.remote.Search
import com.example.appml._model.remote.product.DescriptionsProduct
import com.example.appml._model.remote.product.ProductServer
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MLApi {

    @GET("sites/{SITE_ID}/search")
     suspend fun getSearchApi(@Path("SITE_ID") site_id: String?, @QueryMap params: Map<String, String>?): Search
    //MLA736948974-1744395856
    //https://api.mercadolibre.com/items?ids=MLA881269322
    //https://api.mercadolibre.com/items?ids=MLA736948974&attributes=attributes,pictures,descriptions
    @GET("items")
    suspend fun getProductApi( @QueryMap params: Map<String, String>?): List<ProductServer>
   // https://api.mercadolibre.com/items?ids=MLA736948974/description?id=MLA736948974-1744395856
  //  https://api.mercadolibre.com/items/MLA881269322/descriptions
    @GET("items/{item_id}/descriptions")
    suspend fun getProductDescriptApi( @Path("item_id") item_id: String? ): List<DescriptionsProduct>
}