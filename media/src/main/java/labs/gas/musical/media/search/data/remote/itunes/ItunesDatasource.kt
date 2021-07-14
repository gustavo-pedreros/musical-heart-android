package labs.gas.musical.media.search.data.remote.itunes

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.search.data.model.MediaRemoteModel
import labs.gas.musical.media.search.data.remote.MediaRemoteDatasource

class ItunesDatasource(private val itunesService: ItunesService) : MediaRemoteDatasource {
    override fun searchMedia(query: String): Single<List<MediaRemoteModel>> {
        return itunesService.search(query).map { it.songs }
    }
}
