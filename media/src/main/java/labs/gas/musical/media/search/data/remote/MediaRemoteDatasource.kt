package labs.gas.musical.media.search.data.remote

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.search.data.model.MediaRemoteModel

interface MediaRemoteDatasource {
    fun searchMedia(query: String): Single<List<MediaRemoteModel>>
}
