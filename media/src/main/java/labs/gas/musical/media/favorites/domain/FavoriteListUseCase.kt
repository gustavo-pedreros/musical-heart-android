package labs.gas.musical.media.favorites.domain

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.core.threads.extensions.runOnIo
import labs.gas.musical.media.search.domain.model.MediaDomainModel
import javax.inject.Inject

class FavoriteListUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository, private val scheduler: Scheduler) {
    fun getFavoriteList(): Single<List<MediaDomainModel>> {
        return favoritesRepository.getFavoriteList()
            .runOnIo(scheduler)
    }
}
