package pe.com.gadolfolozano.kotlinzomatoapi.ui.restaurantdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import pe.com.gadolfolozano.kotlinzomatoapi.BR
import pe.com.gadolfolozano.kotlinzomatoapi.R
import pe.com.gadolfolozano.kotlinzomatoapi.databinding.ActivityRestaurantDetailBinding
import pe.com.gadolfolozano.kotlinzomatoapi.ui.model.RestaurantDetailModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import javax.inject.Inject

class RestaurantDetailActivity : BaseActivity<ActivityRestaurantDetailBinding, RestaurantDetailViewModel>() {

    @Inject
    lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_restaurant_detail
    override val viewModel: RestaurantDetailViewModel
        get() = restaurantDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewDataBinding()

        val restaurantDetailModel = intent.getParcelableExtra<RestaurantDetailModel>(RESTAURANT_DETAIL_EXTRA)

        restaurantDetailModel.let {
            getViewDataBinding()?.title?.text = it.name
            getViewDataBinding()?.subtitle?.text = it.address
            getViewDataBinding()?.cuisines?.text = it.cuisines
            if (getViewDataBinding()?.imgRestaurant != null && it.photos != null && it.photos.isNotEmpty()) {
                Glide.with(this).load(it.photos[0]).into(getViewDataBinding()!!.imgRestaurant)
            }
        }
    }

    companion object {
        private const val RESTAURANT_DETAIL_EXTRA = "restaurant_detail_extra"

        fun newIntent(context: Context, restaurantDetailModel: RestaurantDetailModel): Intent {
            val intent = Intent(context, RestaurantDetailActivity::class.java)
            intent.putExtra(RESTAURANT_DETAIL_EXTRA, restaurantDetailModel)
            return intent
        }
    }
}