package labs.gas.musical.media.favorites.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.search.domain.model.MediaDomainModel

interface FavoritesRepository {
    fun addFavorite(media: MediaDomainModel): Completable
    fun deleteFavorite(mediaId: Int): Completable
    fun getFavoriteList(): Single<List<MediaDomainModel>>
}
