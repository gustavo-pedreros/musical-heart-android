package labs.gas.musical.heart.ui.media

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import labs.gas.musical.core.view.DataStatus
import labs.gas.musical.media.search.domain.SearchMediaUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(private val mediaUseCase: SearchMediaUseCase) : ViewModel() {
    val mediaDataStatus: MutableLiveData<DataStatus> = MutableLiveData()

    fun search(query: String) {
        mediaDataStatus.value = DataStatus.Loading
        val disposable = mediaUseCase.searchMedia(query).subscribe(
            {
                it.forEach { media -> Timber.d(media.album) }
                mediaDataStatus.value = DataStatus.Success(it)
            },
            {
                Timber.d(it)
                mediaDataStatus.value = DataStatus.Error(it)
            }
        )
        compositeDisposable.add(disposable)
    }


    protected val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        clearCompositeDisposable()
    }

    private fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}
