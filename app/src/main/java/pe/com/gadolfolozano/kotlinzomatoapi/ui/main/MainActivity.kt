package pe.com.gadolfolozano.kotlinzomatoapi.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pe.com.gadolfolozano.kotlinzomatoapi.BR
import pe.com.gadolfolozano.kotlinzomatoapi.R
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.State
import pe.com.gadolfolozano.kotlinzomatoapi.databinding.ActivityMainBinding
import pe.com.gadolfolozano.kotlinzomatoapi.ui.model.RestaurantMarker
import pe.com.gadolfolozano.kotlinzomatoapi.ui.util.RestaurantInfoWindow
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), OnMapReadyCallback {

    private val MOUNTAIN_VIEW = LatLng(37.4, -122.1)

    @Inject
    lateinit var mainViewModel: MainViewModel

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = mainViewModel

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mainViewModel.isLoading.value = true
        mainViewModel.restaurantMarkers.observe(this,
            Observer { restaurantMarkes ->
                restaurantMarkes.forEach {
                    val market = map.addMarker(MarkerOptions().position(it.latLng))
                    market.tag = it
                }
            })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.map = googleMap

        val cameraPosition = CameraPosition.Builder()
            .target(MOUNTAIN_VIEW)
            .zoom(12f)
            .build()

        map.setInfoWindowAdapter(RestaurantInfoWindow(this))
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        map.setOnCameraIdleListener {
            loadNearbyRestaurants()
        }
        map.setOnInfoWindowClickListener { marker ->
            if (marker.tag is RestaurantMarker) {
                loadRestaurantDetail(marker.tag as RestaurantMarker)
            }
        }
    }

    private fun loadNearbyRestaurants() {
        val centerPoint = map.projection.visibleRegion.latLngBounds.center
        mainViewModel.obtainNearbyRestaurants(centerPoint.latitude, centerPoint.longitude)
            .observe(this, Observer { nearbyRestaurants ->
                when (nearbyRestaurants.state?.status) {
                    State.STATE_LOADING -> showLoading()
                    State.STATE_SUCCESS -> {
                        hideLoading()
                        mainViewModel.createMarkets(nearbyRestaurants.data)
                    }
                    else -> hideLoading()
                }
            })
    }

    private fun loadRestaurantDetail(restaurantMarker: RestaurantMarker) {
        mainViewModel.obtainRestaurantDetail(restaurantMarker.id ?: "")
            .observe(this, Observer { response ->
                when (response.state?.status) {
                    State.STATE_LOADING -> showLoading()
                    State.STATE_SUCCESS -> {
                        hideLoading()
                    }
                    else -> hideLoading()
                }
            })
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
