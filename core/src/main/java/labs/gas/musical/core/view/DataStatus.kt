package labs.gas.musical.core.view

sealed class DataStatus {
    object Loading : DataStatus()
    object Complete : DataStatus()
    object Empty : DataStatus()

    data class Success(var data: Any) : DataStatus() {
        inline fun <reified T> responseTo() = data as T
    }

    data class Error(val error: Throwable) : DataStatus() {
        inline fun <reified T> errorTo() = error as T
    }

}
