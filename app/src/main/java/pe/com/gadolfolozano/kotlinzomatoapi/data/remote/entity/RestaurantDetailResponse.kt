package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity

import com.google.gson.annotations.SerializedName

class RestaurantDetailResponse {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("location")
    var location: LocationResponse? = null

    @SerializedName("cuisines")
    var cuisines: String? = null

    @SerializedName("highlights")
    var highlights: List<String>? = null

    @SerializedName("photos")
    var photos: List<PhotoWrapperResponse>? = null
}