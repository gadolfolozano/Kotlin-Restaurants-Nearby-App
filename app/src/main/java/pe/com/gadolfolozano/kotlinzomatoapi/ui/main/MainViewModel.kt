package pe.com.gadolfolozano.kotlinzomatoapi.ui.main

import androidx.lifecycle.LiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.DataManager
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

import javax.inject.Inject

class MainViewModel @Inject
constructor(private val dataManager: DataManager) : BaseViewModel() {

    public fun obtainNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): LiveData<StateData<NearbyRestaurantsResponse>> {
        return dataManager.obtainNearbyRestaurants(latitude, longitude);
    }

}
