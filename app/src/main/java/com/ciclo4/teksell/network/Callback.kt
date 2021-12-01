package com.ciclo4.teksell.network

import java.lang.Exception

interface Callback<T> {
    fun OnSuccess(result:T?)

    fun OnFailed(exception: Exception)
}
