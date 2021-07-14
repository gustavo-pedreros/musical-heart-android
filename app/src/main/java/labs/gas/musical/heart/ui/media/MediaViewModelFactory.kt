package labs.gas.musical.heart.ui.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import labs.gas.musical.media.search.domain.SearchMediaUseCase

@Suppress("UNCHECKED_CAST")
class MediaViewModelFactory(private val mediaUseCase: SearchMediaUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MediaViewModel(mediaUseCase) as T
}
