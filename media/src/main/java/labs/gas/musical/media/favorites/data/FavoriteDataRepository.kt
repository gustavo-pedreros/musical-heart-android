package labs.gas.musical.media.favorites.data

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.favorites.data.local.FavoritesLocalDatasource
import labs.gas.musical.media.favorites.domain.FavoritesRepository
import labs.gas.musical.media.search.domain.model.MediaDomainModel

class FavoritesDataRepository(private val favoritesLocalDatasource: FavoritesLocalDatasource) : FavoritesRepository {
    override fun addFavorite(media: MediaDomainModel): Completable {
        return favoritesLocalDatasource.saveMedia(media)
    }

    override fun deleteFavorite(mediaId: Int): Completable {
        return favoritesLocalDatasource.deleteMedia(mediaId)
    }

    override fun getFavoriteList(): Single<List<MediaDomainModel>> {
        return favoritesLocalDatasource.getMediaList()
    }
}
