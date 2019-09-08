package pe.com.gadolfolozano.kotlinzomatoapi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import pe.com.gadolfolozano.kotlinzomatoapi.data.DataManager
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.RestaurantDetailResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData
import pe.com.gadolfolozano.kotlinzomatoapi.ui.model.RestaurantMarker
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

import javax.inject.Inject

class MainViewModel @Inject
constructor(private val dataManager: DataManager) : BaseViewModel() {

    val restaurantMarkers = MutableLiveData<List<RestaurantMarker>>()

    fun obtainNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): LiveData<StateData<NearbyRestaurantsResponse>> {
        return dataManager.obtainNearbyRestaurants(latitude, longitude);
    }

    fun obtainRestaurantDetail(
        restaurantId: String
    ): LiveData<StateData<RestaurantDetailResponse>> {
        return dataManager.obtainRestaurantDetail(restaurantId);
    }

    fun createMarkets(data: NearbyRestaurantsResponse?) {
        val restaurants = data?.nearbyRestaurants?.map {
            RestaurantMarker(
                it.restaurant?.id,
                it.restaurant?.name,
                it.restaurant?.thumb,
                it.restaurant?.location?.address,
                LatLng(
                    it.restaurant?.location?.latitude ?: 0.0,
                    it.restaurant?.location?.longitude ?: 0.0
                )
            )
        }

        restaurantMarkers.value = restaurants
    }

}
