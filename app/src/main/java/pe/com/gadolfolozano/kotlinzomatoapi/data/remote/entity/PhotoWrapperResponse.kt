package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity

import com.google.gson.annotations.SerializedName

class PhotoWrapperResponse {
    @SerializedName("photo")
    var photo: PhotoResponse? = null
}