package pe.com.gadolfolozano.kotlinzomatoapi.ui.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import pe.com.gadolfolozano.kotlinzomatoapi.R
import pe.com.gadolfolozano.kotlinzomatoapi.ui.model.RestaurantMarker

class RestaurantInfoWindow(private val context: Context) : GoogleMap.InfoWindowAdapter {

    private val view: View

    init {
        val inflater =
            this.context.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater.inflate(R.layout.layout_info_window, null)
    }


    override fun getInfoWindow(marker: Marker?): View? {
        if (marker != null && marker.isInfoWindowShown) {
            marker.hideInfoWindow()
            marker.showInfoWindow()
        }
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val imgThumb = view.findViewById<ImageView>(R.id.img_thumb)
        val txtTitle = view.findViewById<TextView>(R.id.txt_title)
        val txtDetail = view.findViewById<TextView>(R.id.txt_detail)

        if (marker.tag is RestaurantMarker) {
            val restaurantMarker = marker.tag as RestaurantMarker?

            txtTitle.text = restaurantMarker?.name
            txtDetail.text = restaurantMarker?.address
            Glide.with(context).load(restaurantMarker?.thumb).into(imgThumb)
        }

        return view
    }
}
