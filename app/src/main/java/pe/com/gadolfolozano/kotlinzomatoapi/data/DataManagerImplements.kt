package pe.com.gadolfolozano.kotlinzomatoapi.data

import androidx.lifecycle.LiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.ApiHelper
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.RestaurantDetailResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImplements @Inject constructor(
    val apiHelper: ApiHelper
) : DataManager {

    override fun obtainNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): LiveData<StateData<NearbyRestaurantsResponse>> {
        return apiHelper.obtainNearbyRestaurants(latitude, longitude)
    }

    override fun obtainRestaurantDetail(
        restaurantId: String
    ): LiveData<StateData<RestaurantDetailResponse>> {
        return apiHelper.obtainRestaurantDetail(restaurantId)
    }

}