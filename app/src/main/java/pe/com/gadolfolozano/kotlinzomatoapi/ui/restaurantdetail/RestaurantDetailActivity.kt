package pe.com.gadolfolozano.kotlinzomatoapi.ui.restaurantdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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
            getViewDataBinding()?.vpImages?.adapter = ImagePageAdapter(it.photos, supportFragmentManager)
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

    class ImagePageAdapter constructor(val images: List<String>?, fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return images?.size ?: 0
        }

        override fun getItem(position: Int): Fragment {
            return RestaurantDetailImageFragment.newInstance(images?.get(position) ?: "")
        }
    }
}