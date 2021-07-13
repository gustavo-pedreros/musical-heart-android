package labs.gas.musical.core.view

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class RxViewModel : ViewModel() {
    protected val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        clearCompositeDisposable()
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}
