package com.be_ur_boss.android_user_credentials.login_ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.be_ur_boss.android_user_credentials.R
import com.be_ur_boss.android_user_credentials.databinding.FragmentLoginUiTwoBinding
import com.be_ur_boss.android_user_credentials.forgot_password_ui.FragmentForgotPassUiOne
import com.be_ur_boss.android_user_credentials.signup_ui.FragmentSignUpUiOne
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsFactory
import com.be_ur_boss.android_user_credentials.viewmodel.CredentialsViewModel
import com.be_ur_boss.android_user_credentials.webservices.Status


class FragmentLoginUiTwo : Fragment() {
    private lateinit var binding: FragmentLoginUiTwoBinding
    private lateinit var viewModel: CredentialsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginUiTwoBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = ViewModelProvider(this, CredentialsFactory()).get(CredentialsViewModel::class.java)

        viewModel.loginEmailValidationObserver.observe(requireActivity(), Observer {
           if(!it) {
               binding.edEmail.error = "Please enter valid email"
           }
        })
        viewModel.loginPasswordValidationObserver.observe(requireActivity(), Observer {
           if(!it) {
               binding.edPassword.error = "Please enter 8 digit password"
           }
        })

        // Api flow testing
        viewModel.proceedToLoginObserver.observe(requireActivity(), Observer {
         if(it) {
             viewModel.callLoginApi(binding.edEmail.text.toString()
                 ,binding.edPassword.text.toString()).observe(requireActivity(), Observer {
                 it?.let { resource ->
                     when (resource.status) {
                         Status.SUCCESS -> {
                             resource.data?.let {    }
                         }
                         Status.ERROR -> {
                             Toast.makeText(activity, ""+it, Toast.LENGTH_LONG).show()
                         }
                         Status.LOADING -> {

                         }
                     }
                 }
             })
         }

        })

        binding.tvRegister.setOnClickListener {
            openFragment(FragmentSignUpUiOne())
        }

        binding.tvForgotPassword.setOnClickListener {
            openFragment(FragmentForgotPassUiOne())
        }

        binding.btLogin.setOnClickListener {
            viewModel.signInNow(binding.edEmail.text.toString()
            ,binding.edPassword.text.toString())
        }
        return view
    }
    private fun openFragment(fragment: Fragment) {
        val manager: FragmentManager = requireFragmentManager()
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.container, fragment, fragment.tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}