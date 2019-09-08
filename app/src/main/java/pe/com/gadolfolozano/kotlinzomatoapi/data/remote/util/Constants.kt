package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util

class Constants private constructor() {

    init {
        throw IllegalStateException("Utility class")
    }

    companion object {

        val BASE_URL = "https://developers.zomato.com"

        val API_KEY = "e7437e76decba6bf0c90b58f42b5d9b2"
    }

}
