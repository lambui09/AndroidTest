package com.android.pixelteam.buiduclamtest.data.remote.middleware

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response



class InterceptorImpl : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = initializeHeader(chain)
        val request = builder.build()

        return chain.proceed(request)
    }
    private fun initializeHeader(chain: Interceptor.Chain) : Request.Builder{
        val originRequest = chain.request()
        return originRequest.newBuilder()
            .header("Accept", "application/json")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Cache-Control", "no-store")
            .method(originRequest.method, originRequest.body)
    }
}