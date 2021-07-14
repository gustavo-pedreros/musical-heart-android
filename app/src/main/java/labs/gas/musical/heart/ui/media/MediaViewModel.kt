package labs.gas.musical.heart.ui.media

import androidx.lifecycle.MutableLiveData
import labs.gas.musical.core.view.DataStatus
import labs.gas.musical.core.view.RxViewModel
import labs.gas.musical.media.search.domain.SearchMediaUseCase
import timber.log.Timber

class MediaViewModel(private val mediaUseCase: SearchMediaUseCase) : RxViewModel() {
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

}
