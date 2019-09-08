package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity

import com.google.gson.annotations.SerializedName


class RestaurantResponse {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("thumb")
    var thumb: String? = null

    @SerializedName("location")
    var location: LocationResponse? = null

}
