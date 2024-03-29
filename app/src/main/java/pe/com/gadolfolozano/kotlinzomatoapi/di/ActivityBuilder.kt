package pe.com.gadolfolozano.kotlinzomatoapi.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.com.gadolfolozano.kotlinzomatoapi.ui.main.MainActivity
import pe.com.gadolfolozano.kotlinzomatoapi.ui.restaurantdetail.RestaurantDetailActivity
import pe.com.gadolfolozano.kotlinzomatoapi.ui.splash.SplashActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector()
    internal abstract fun bindRestaurantDetailActivity(): RestaurantDetailActivity

}