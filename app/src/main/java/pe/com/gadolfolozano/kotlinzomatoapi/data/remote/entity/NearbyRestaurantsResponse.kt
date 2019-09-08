package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity

import com.google.gson.annotations.SerializedName

class NearbyRestaurantsResponse {

    @SerializedName("nearby_restaurants")
    var nearbyRestaurants: List<RestaurantWrapperResponse>? = null

}
