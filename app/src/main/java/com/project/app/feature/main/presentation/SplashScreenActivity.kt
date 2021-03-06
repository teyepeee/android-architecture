package com.project.app.feature.main.presentation

import android.os.Bundle
import com.project.app.R
import com.project.app.databinding.ActivitySplashScreenBinding
import com.project.framework.core.BaseActivity
import com.project.framework.core.owner.ViewDataBindingOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by achmad.fachrudin on 3-Jun-19
 */
class SplashScreenActivity : BaseActivity<SplashScreenViewModel>(),
    SplashScreenView,
    ViewDataBindingOwner<ActivitySplashScreenBinding> {

    override val layoutResourceId = R.layout.activity_splash_screen
    override lateinit var binding: ActivitySplashScreenBinding
    override val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            delay(2100)
            MainActivity.startThisActivity(this@SplashScreenActivity)
            finish()
        }
    }
}