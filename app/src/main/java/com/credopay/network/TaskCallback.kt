package com.credopay.network

interface TaskCallback<T> {

    fun onComplete(result: T?)
    fun onException(t: Throwable?)

}