package com.cahyadesthian.learnexercisethree.networking
import com.cahyadesthian.learnexercisethree.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * [Interceptor Header Req]
 * to adding header on request api
 * not good to implemnt api github in class,
 * so it placed on buildconfig
 * */

class InterceptorHeader : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization","token ${BuildConfig.API_KEY_EXERCISE_THREE_REFERENCE}")
            .build()
        return chain.proceed(request)
    }



}