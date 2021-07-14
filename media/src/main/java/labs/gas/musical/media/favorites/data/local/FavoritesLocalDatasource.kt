package labs.gas.musical.media.favorites.data.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.search.domain.model.MediaDomainModel

interface FavoritesLocalDatasource {
    fun saveMedia(media: MediaDomainModel): Completable
    fun deleteMedia(mediaId: Int): Completable
    fun getMediaList(): Single<List<MediaDomainModel>>
    fun getMediaIdList(): Single<List<Int>>
    fun deleteAll(): Completable
}
