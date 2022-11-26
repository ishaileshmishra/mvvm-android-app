package com.be_ur_boss.android_user_credentials

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.be_ur_boss.android_user_credentials.databinding.ActivityMainBinding
import com.be_ur_boss.android_user_credentials.login_ui.FragmentLoginUiOne
import com.be_ur_boss.android_user_credentials.login_ui.FragmentLoginUiTwo

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btLoginUi1.setOnClickListener { 
           openFragment(FragmentLoginUiOne())
        }
        binding.btLoginUi2.setOnClickListener {
           openFragment(FragmentLoginUiTwo())
        }
    }


    private fun openFragment(fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.container, fragment, fragment.tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}