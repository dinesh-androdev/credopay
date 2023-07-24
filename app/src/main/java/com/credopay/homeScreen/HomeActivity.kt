package com.credopay.homeScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.credopay.R
import com.credopay.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeScreenViewModel>()
    private lateinit var dataBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        dataBinding.apply {
            lifecycleOwner = this@HomeActivity
            viewmodel = viewModel
        }
        viewModel.getAllCharacters(this@HomeActivity)
    }

}