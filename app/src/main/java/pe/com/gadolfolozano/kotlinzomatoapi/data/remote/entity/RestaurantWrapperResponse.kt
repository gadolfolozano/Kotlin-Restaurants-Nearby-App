package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity

import com.google.gson.annotations.SerializedName

class RestaurantWrapperResponse {

    @SerializedName("restaurant")
    var restaurant: RestaurantResponse? = null

}
