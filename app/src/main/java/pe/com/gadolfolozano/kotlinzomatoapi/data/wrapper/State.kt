package pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper

class State {

    var status: Int = 0
    var error: Throwable? = null

    init {
        this.status = STATE_CREATED
    }

    fun loading(): State {
        this.status = STATE_LOADING
        this.error = null
        return this
    }

    fun error(error: Throwable): State {
        this.status = STATE_ERROR
        this.error = error
        return this
    }

    fun success(): State {
        this.status = STATE_SUCCESS
        this.error = null
        return this
    }

    companion object {
        val STATE_CREATED = 100
        val STATE_LOADING = 101
        val STATE_ERROR = 102
        val STATE_SUCCESS = 103
    }
}
