package labs.gas.musical.media.search.data.remote.itunes

import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.search.data.model.MediaResponseRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {
    @GET("search")
    fun search(@Query("term") artist: String): Single<MediaResponseRemoteModel>
}
