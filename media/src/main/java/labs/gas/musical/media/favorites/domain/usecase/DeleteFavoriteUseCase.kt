package labs.gas.musical.media.favorites.domain.usecase

import io.reactivex.rxjava3.core.Completable
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.core.threads.extensions.runOnIo
import labs.gas.musical.media.favorites.domain.FavoritesRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository, private val scheduler: Scheduler) {
    fun deleteFavorite(mediaId: Int): Completable {
        return favoritesRepository.deleteFavorite(mediaId)
            .runOnIo(scheduler)
    }
}
