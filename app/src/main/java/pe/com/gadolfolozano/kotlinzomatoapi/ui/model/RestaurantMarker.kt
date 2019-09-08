package pe.com.gadolfolozano.kotlinzomatoapi.ui.model

import com.google.android.gms.maps.model.LatLng

class RestaurantMarker
constructor(val name: String?,
            val thumb: String?,
            val address: String?,
            val latLng: LatLng)