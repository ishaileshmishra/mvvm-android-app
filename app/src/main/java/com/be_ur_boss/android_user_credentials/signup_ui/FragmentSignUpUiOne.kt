package com.be_ur_boss.android_user_credentials.signup_ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.be_ur_boss.android_user_credentials.databinding.FragmentSignUpUiOneBinding
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsFactory
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsViewModel

class FragmentSignUpUiOne : Fragment() {
    private lateinit var binding: FragmentSignUpUiOneBinding
    private lateinit var viewModel: CredentialsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpUiOneBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel =
            ViewModelProvider(this, CredentialsFactory()).get(CredentialsViewModel::class.java)

        viewModel.signUpNameValidationObserver.observe(requireActivity(), Observer {
            if (!it) {
                binding.edFullName.error = "Please enter valid name"
            }
        })

        viewModel.signUpEmailValidationObserver.observe(requireActivity(), Observer {
            if (!it) {
                binding.edEmail.error = "Please enter valid email"
            }
        })

        viewModel.signUpMobileValidationObserver.observe(requireActivity(), Observer {
            if (!it) {
                binding.edMobile.error = "Please enter valid 10 digit mobile"
            }
        })

        viewModel.signUpMPasswordValidationObserver.observe(requireActivity(), Observer {
            if (!it) {
                binding.edPassword.error = "Please enter 8 digit password"
            }
        })
        viewModel.signUpConfPasswordValidationObserver.observe(requireActivity(), Observer {
            if (!it) {
                binding.edConfirmPassword.error = "Please enter 8 digit password"
            }
        })
        viewModel.signUpPasswordMatchValidationObserver.observe(requireActivity(), Observer {
            if (!it) {
                binding.edConfirmPassword.error = "Password not matched"
            }
        })


        binding.btSignUp.setOnClickListener {
            viewModel.signUpNow(
                binding.edFullName.text.toString(),
                binding.edEmail.text.toString(),
                binding.edMobile.text.toString(),
                binding.edPassword.text.toString(),
                binding.edConfirmPassword.text.toString()
            )
        }

        binding.tvLogin.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

}