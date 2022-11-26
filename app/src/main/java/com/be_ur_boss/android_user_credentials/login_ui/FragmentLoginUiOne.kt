package com.be_ur_boss.android_user_credentials.login_ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.be_ur_boss.android_user_credentials.databinding.FragmentLoginUiOneBinding
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsFactory
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsRepository
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsViewModel

class FragmentLoginUiOne : Fragment() {
    private lateinit var binding: FragmentLoginUiOneBinding
    private lateinit var viewModel: CredentialsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginUiOneBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = ViewModelProvider(this, CredentialsFactory()).get(CredentialsViewModel::class.java)
        return view
    }

}