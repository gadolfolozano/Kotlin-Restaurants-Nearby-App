package pe.com.gadolfolozano.kotlinzomatoapi.ui.splash

import android.os.Bundle
import pe.com.gadolfolozano.kotlinzomatoapi.BR
import pe.com.gadolfolozano.kotlinzomatoapi.R
import pe.com.gadolfolozano.kotlinzomatoapi.ui.base.BaseActivity
import javax.inject.Inject
import pe.com.gadolfolozano.kotlinzomatoapi.databinding.ActivitySplashBinding
import pe.com.gadolfolozano.kotlinzomatoapi.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    @Inject
    lateinit var mSplashViewModel: SplashViewModel

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = mSplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewDataBinding()?.buttonStart?.setOnClickListener{
            val intent = MainActivity.newIntent(this@SplashActivity)
            startActivity(intent)
            finish()
        }
    }
}