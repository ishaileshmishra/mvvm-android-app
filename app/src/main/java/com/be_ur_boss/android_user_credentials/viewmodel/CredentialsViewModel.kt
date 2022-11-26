package com.be_ur_boss.android_user_credentials.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.be_ur_boss.android_user_credentials.webservices.Resource
import kotlinx.coroutines.Dispatchers

class CredentialsViewModel(private val credentialsRepository: CredentialsRepository) : ViewModel() {

    internal val loginEmailValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val loginPasswordValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val proceedToLoginObserver: MutableLiveData<Boolean> = MutableLiveData()

    internal val signUpNameValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val signUpEmailValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val signUpMobileValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val signUpMPasswordValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val signUpConfPasswordValidationObserver: MutableLiveData<Boolean> = MutableLiveData()
    internal val signUpPasswordMatchValidationObserver: MutableLiveData<Boolean> = MutableLiveData()


    internal fun signInNow(
        email: String,
        password: String
    ) {
        if (!emailValidator(email)) {
            loginEmailValidationObserver.postValue(false)
        } else if (password.length < 8) {
            loginPasswordValidationObserver.postValue(false)
        } else {
            proceedToLoginObserver.postValue(true)
        }
    }

    fun callLoginApi(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = credentialsRepository.loginApiCall(
                        email,
                        password
                    ).results
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    internal fun signUpNow(
        fullName: String,
        email: String,
        mobile: String,
        password: String,
        confPassword: String
    ) {

        if (!fullName.isNotEmpty()) {
            signUpNameValidationObserver.postValue(false)
        } else if (!emailValidator(email)) {
            signUpEmailValidationObserver.postValue(false)
        } else if (!mobile.isNotEmpty()) {
            signUpMobileValidationObserver.postValue(false)
        } else if (password.length < 8 || password != confPassword) {
            signUpMPasswordValidationObserver.postValue(false)
        } else if (confPassword.length < 8) {
            signUpConfPasswordValidationObserver.postValue(false)
        } else if (password != confPassword) {
            signUpPasswordMatchValidationObserver.postValue(false)
        } else {
            credentialsRepository.signUpApiCall(fullName, email, mobile, password)
        }
    }

    private fun emailValidator(etMail: String) =
        (((etMail.isNotEmpty()) && Patterns.EMAIL_ADDRESS.matcher(etMail).matches()))
}