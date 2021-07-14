package labs.gas.musical.media.search.domain

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.search.domain.model.MediaDomainModel

interface MediaRepository {
    fun searchMedia(query:String): Single<List<MediaDomainModel>>
}
