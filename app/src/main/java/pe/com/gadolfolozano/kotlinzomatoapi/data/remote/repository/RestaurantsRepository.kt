package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.repository

import androidx.lifecycle.LiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.RestaurantDetailResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util.ApiInterface
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util.RetrofitLiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantsRepository @Inject
constructor(private val apiInterface: ApiInterface) {

    fun obtainNearbyRestaurants(latitude: Double, longitude: Double): LiveData<StateData<NearbyRestaurantsResponse>> {
        return RetrofitLiveData(apiInterface.obtainNearbyRestaurants(latitude, longitude))
    }

    fun obtainRestaurantDetail(restaurantId: String): RetrofitLiveData<RestaurantDetailResponse> {
        return RetrofitLiveData(apiInterface.obtainRestaurantDetail(restaurantId))
    }
}
