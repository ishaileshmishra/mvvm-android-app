package com.be_ur_boss.android_user_credentials.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.be_ur_boss.android_user_credentials.webservices.ApiHelper
import com.be_ur_boss.android_user_credentials.webservices.RetrofitBuilder

class CredentialsFactory():Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CredentialsViewModel::class.java)){
            var credentialsRepository=CredentialsRepository(ApiHelper(RetrofitBuilder.apiService))
            return CredentialsViewModel(credentialsRepository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}