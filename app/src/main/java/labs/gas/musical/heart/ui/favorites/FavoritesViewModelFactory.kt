package labs.gas.musical.heart.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import labs.gas.musical.media.favorites.domain.AddFavoriteUseCase
import labs.gas.musical.media.favorites.domain.DeleteFavoriteUseCase
import labs.gas.musical.media.favorites.domain.FavoriteListUseCase

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory(
    private val saveFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val favoriteListUseCase: FavoriteListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FavoritesViewModel(saveFavoriteUseCase, deleteFavoriteUseCase, favoriteListUseCase) as T
}
