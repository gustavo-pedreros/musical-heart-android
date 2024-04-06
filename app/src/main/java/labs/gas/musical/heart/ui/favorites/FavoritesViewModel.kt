package labs.gas.musical.heart.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import labs.gas.musical.core.view.DataStatus
import labs.gas.musical.media.favorites.domain.usecase.AddFavoriteUseCase
import labs.gas.musical.media.favorites.domain.usecase.DeleteFavoriteUseCase
import labs.gas.musical.media.favorites.domain.usecase.FavoriteListUseCase
import labs.gas.musical.media.search.domain.model.MediaDomainModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val saveFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val favoriteListUseCase: FavoriteListUseCase
) : ViewModel() {
    val saveFavoriteDataStatus: MutableLiveData<DataStatus> = MutableLiveData()
    val deleteFavoriteDataStatus: MutableLiveData<DataStatus> = MutableLiveData()
    val favoriteListDataStatus: MutableLiveData<DataStatus> = MutableLiveData()

    fun saveFavorite(media: MediaDomainModel) {
        saveFavoriteDataStatus.value = DataStatus.Loading
        val disposable = saveFavoriteUseCase.addFavorite(media).subscribe(
            { saveFavoriteDataStatus.value = DataStatus.Complete },
            { saveFavoriteDataStatus.value = DataStatus.Error(it) }
        )
        compositeDisposable.add(disposable)
    }

    fun deleteFavorite(media: MediaDomainModel) {
        deleteFavoriteDataStatus.value = DataStatus.Loading
        val disposable = deleteFavoriteUseCase.deleteFavorite(media.id).subscribe(
            { deleteFavoriteDataStatus.value = DataStatus.Complete },
            { deleteFavoriteDataStatus.value = DataStatus.Error(it) }
        )
        compositeDisposable.add(disposable)
    }

    fun favoriteList() {
        favoriteListDataStatus.value = DataStatus.Loading
        val disposable = favoriteListUseCase.getFavoriteList().subscribe(
            {
                if (it.isEmpty()) {
                    favoriteListDataStatus.value = DataStatus.Empty
                } else {
                    favoriteListDataStatus.value = DataStatus.Success(it)
                }
            },
            { favoriteListDataStatus.value = DataStatus.Error(it) }
        )
        compositeDisposable.add(disposable)
    }

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        clearCompositeDisposable()
    }

    private fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}
