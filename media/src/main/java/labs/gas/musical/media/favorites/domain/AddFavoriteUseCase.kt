package labs.gas.musical.media.favorites.domain

import io.reactivex.rxjava3.core.Completable
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.core.threads.extensions.runOnIo
import labs.gas.musical.media.search.domain.model.MediaDomainModel

class AddFavoriteUseCase(private val favoritesRepository: FavoritesRepository, private val scheduler: Scheduler) {
    fun addFavorite(media: MediaDomainModel): Completable {
        return favoritesRepository.addFavorite(media)
            .runOnIo(scheduler)
    }
}
