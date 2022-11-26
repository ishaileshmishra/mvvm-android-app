package com.be_ur_boss.android_user_credentials.webservices

class ApiHelper(private val apiService: ApiService) {
    suspend fun getItunesSongs() = apiService.getItunesSongs()
}