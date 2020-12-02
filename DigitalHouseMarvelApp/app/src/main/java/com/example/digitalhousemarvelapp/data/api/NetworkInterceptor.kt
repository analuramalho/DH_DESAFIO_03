package com.example.digitalhousemarvelapp.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val ts = System.currentTimeMillis().toString()
        val apikey = PUBLIC_KEY

        val httpUrl = request.url().newBuilder()
            .addQueryParameter(TS, ts)
            .addQueryParameter(API_KEY, apikey)
            .addQueryParameter(HASH, getHash(ts))
            .build()

        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    fun getHash(ts: String) = "${ts}$PRIVATE_KEY$PUBLIC_KEY".md5

    private val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

    companion object {
        private const val TS = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"

        private const val PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f"
        const val PUBLIC_KEY = "6eb7e8896ec5850c52515a8a23ee97f0"

        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
        }
    }
}