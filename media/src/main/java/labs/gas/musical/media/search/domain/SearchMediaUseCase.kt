package labs.gas.musical.media.search.domain

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.core.threads.extensions.runOnIo
import labs.gas.musical.media.search.domain.model.MediaDomainModel

class SearchMediaUseCase(private val mediaRepository: MediaRepository, private val scheduler: Scheduler) {
    fun searchMedia(query: String): Single<List<MediaDomainModel>> {
        return mediaRepository.searchMedia(query)
            .runOnIo(scheduler)
    }
}
