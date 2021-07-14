package labs.gas.musical.media.favorites.data.local.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.favorites.data.local.FavoritesLocalDatasource
import labs.gas.musical.media.favorites.data.local.room.model.toDomainModel
import labs.gas.musical.media.favorites.data.local.room.model.toRoomModel
import labs.gas.musical.media.search.domain.model.MediaDomainModel

class FavoritesRoomDatasource(private val favoritesMediaDao: FavoritesMediaDao) : FavoritesLocalDatasource {
    override fun saveMedia(media: MediaDomainModel): Completable {
        return Completable.fromAction {
            favoritesMediaDao.saveMedia(media.toRoomModel())
        }
    }

    override fun deleteMedia(mediaId: Int): Completable {
        return Completable.fromAction {
            favoritesMediaDao.deleteMedia(mediaId)
        }
    }

    override fun getMediaList(): Single<List<MediaDomainModel>> {
        return favoritesMediaDao.getMediaList().map { list -> list.map { it.toDomainModel() } }
    }

    override fun getMediaIdList(): Single<List<Int>> {
        return favoritesMediaDao.getMediaIdList()
    }

    override fun deleteAll(): Completable {
        return Completable.fromAction {
            favoritesMediaDao.deleteAll()
        }
    }
}
