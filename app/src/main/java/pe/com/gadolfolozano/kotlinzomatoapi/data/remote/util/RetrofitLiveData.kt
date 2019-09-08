package pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util

import androidx.lifecycle.LiveData
import pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper.StateData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitLiveData<T>(private val call: Call<T>) : LiveData<StateData<T>>(), Callback<T> {
    private val stateData: StateData<T>

    init {
        this.stateData = StateData()
    }

    override fun onActive() {
        if (!call.isCanceled && !call.isExecuted) {
            call.enqueue(this)
            value = stateData.loading()
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.body() != null) {
            value = stateData.success(response.body()!!)
        } else {
            value = stateData.error(Throwable())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        value = stateData.error(t)
    }
}
