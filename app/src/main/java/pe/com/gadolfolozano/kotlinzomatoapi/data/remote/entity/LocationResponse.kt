package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity

import com.google.gson.annotations.SerializedName

class LocationResponse {
    @SerializedName("address")
    var address: String? = null

    @SerializedName("latitude")
    var latitude: Double? = null

    @SerializedName("longitude")
    var longitude: Double? = null
}
