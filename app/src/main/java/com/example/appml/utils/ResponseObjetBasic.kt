package com.example.appml.utils

interface ResponseObjetBasic<T> {

    fun onSuccess(entity: T)

    fun onError(message: String)

}