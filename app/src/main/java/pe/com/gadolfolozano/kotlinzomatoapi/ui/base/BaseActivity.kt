package pe.com.gadolfolozano.mymovie.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import dagger.android.AndroidInjection
import pe.com.gadolfolozano.kotlinzomatoapi.ui.util.CommonUtils

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private var progressDialog: ProgressDialog? = null
    private var viewDataBinding: T? = null
    private var viewModel1: V? = null

    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * @return view model instance
     */
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()

        observeLoading()
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this, Observer { loading ->
            if (loading) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.cancel()
        }
    }

    fun showLoading() {
        hideLoading()
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    fun getViewDataBinding(): T? {
        return viewDataBinding
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.viewModel1 = if (viewModel1 == null) viewModel else viewModel1
        viewDataBinding?.setVariable(bindingVariable, viewModel1)
        viewDataBinding?.executePendingBindings()
    }
}
