package com.example.coroutineskata.api

import android.content.Context
import android.net.ConnectivityManager
import com.example.coroutineskata.internal.Exceptions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityIntercpetorImpl(context: Context) : ConnectivityIntercpetor {
    val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnlline())
            throw NoConnectivityException("No internet connectivity exception")
        return chain.proceed(chain.request())
    }

    private fun isOnlline() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnected

    }


}