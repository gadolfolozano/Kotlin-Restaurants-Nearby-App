package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util

import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.NearbyRestaurantsResponse
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.entity.RestaurantDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/v2.1/geocode")
    fun obtainNearbyRestaurants(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Call<NearbyRestaurantsResponse>

    @GET("/api/v2.1/restaurant")
    fun obtainRestaurantDetail(
        @Query("res_id") restaurantId: String
    ): Call<RestaurantDetailResponse>
}
