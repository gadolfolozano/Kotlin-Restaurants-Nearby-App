package pe.com.gadolfolozano.kotlinzomatoapi.data.wrapper

class StateData<T> {

    var state: State? = null
    var data: T? = null

    constructor() {
        state = State()
    }

    constructor(state: State, data: T) {
        this.state = state
        this.data = data
    }

    fun loading(): StateData<T> {
        this.state = State().loading()
        this.data = null
        return this
    }

    fun error(error: Throwable): StateData<T> {
        this.state = State().error(error)
        this.data = null
        return this
    }

    fun success(data: T): StateData<T> {
        this.state = State().success()
        this.data = data
        return this
    }
}
