package pe.com.gadolfolozano.kotlinzomatoapi.data.remote

import androidx.lifecycle.LiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.RestaurantDetailResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.repository.RestaurantsRepository
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelperImplements @Inject
constructor(private val restaurantsRepository: RestaurantsRepository) : ApiHelper {

    override fun obtainNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): LiveData<StateData<NearbyRestaurantsResponse>> {
        return restaurantsRepository.obtainNearbyRestaurants(latitude, longitude);
    }

    override fun obtainRestaurantDetail(
        restaurantId: String
    ): LiveData<StateData<RestaurantDetailResponse>> {
        return restaurantsRepository.obtainRestaurantDetail(restaurantId)
    }

}