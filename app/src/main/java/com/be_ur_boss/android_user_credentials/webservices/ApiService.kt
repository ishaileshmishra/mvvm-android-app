package com.be_ur_boss.android_user_credentials.webservices

import com.be_ur_boss.android_user_credentials.models.Songs
import retrofit2.http.GET

interface ApiService {
    @GET("search?term=Michael+jackson&media=musicVideo")
    suspend fun getItunesSongs(): Songs
}