package pe.com.gadolfolozano.mymovie.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
}
