package pe.com.gadolfolozano.kotlinzomatoapi.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pe.com.gadolfolozano.kotlinzomatoapi.R

class RestaurantDetailImageFragment : Fragment() {
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(ARG_IMAGE_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_restaurant_detail_image, container, false)
        val imgRestaurant = fragmentView.findViewById<ImageView>(R.id.img_restaurant)
        imageUrl.let {
            Glide.with(this).load(it).diskCacheStrategy(DiskCacheStrategy.ALL).into(imgRestaurant)
        }
        return fragmentView
    }

    companion object {

        private const val ARG_IMAGE_URL = "ARG_IMAGE_URL"

        @JvmStatic
        fun newInstance(imageUrl: String) =
            RestaurantDetailImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE_URL, imageUrl)
                }
            }
    }
}
