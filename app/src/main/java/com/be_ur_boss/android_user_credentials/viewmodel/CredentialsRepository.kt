package com.be_ur_boss.android_user_credentials.viewmodel
import com.be_ur_boss.android_user_credentials.webservices.ApiHelper

class CredentialsRepository(private val apiHelper: ApiHelper) {

    suspend fun loginApiCall(email: String, password: String) = apiHelper.getItunesSongs()



    fun signUpApiCall(fullName: String, email: String, mobile: String, password: String) {

    }
}