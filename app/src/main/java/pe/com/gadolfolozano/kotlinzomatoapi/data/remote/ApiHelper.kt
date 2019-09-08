package pe.com.gadolfolozano.kotlinzomatoapi.data.remote

import androidx.lifecycle.LiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData

interface ApiHelper {
    fun obtainNearbyRestaurants(latitude: Double, longitude: Double)
            : LiveData<StateData<NearbyRestaurantsResponse>>
}