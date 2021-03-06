package labs.gas.musical.media.search.data

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.favorites.data.local.FavoritesLocalDatasource
import labs.gas.musical.media.search.data.model.toDomainModel
import labs.gas.musical.media.search.data.remote.MediaRemoteDatasource
import labs.gas.musical.media.search.domain.MediaRepository
import labs.gas.musical.media.search.domain.model.MediaDomainModel

class MediaDataRepository(
    private val remoteDatasource: MediaRemoteDatasource,
    private val favoritesLocalDatasource: FavoritesLocalDatasource
) : MediaRepository {
    override fun searchMedia(query: String): Single<List<MediaDomainModel>> {
        return favoritesLocalDatasource.getMediaIdList().flatMap { favoriteList ->
            remoteDatasource.searchMedia(query)
                .map {
                    it.map { remoteModel ->
                        remoteModel.toDomainModel().apply { isFavorite = favoriteList.contains(remoteModel.songId) }
                    }
                }

        }
    }
}
