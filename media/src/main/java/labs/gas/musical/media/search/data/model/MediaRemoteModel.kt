package labs.gas.musical.media.search.data.model

import com.google.gson.annotations.SerializedName
import labs.gas.musical.core.extensions.empty
import labs.gas.musical.media.search.domain.model.MediaDomainModel

class MediaResponseRemoteModel(
    @SerializedName("resultCount") val count: Int,
    @SerializedName("results") val songs: List<MediaRemoteModel>
)

class MediaRemoteModel(
    @SerializedName("trackId") val songId: Int,
    @SerializedName("trackName") val songName: String? = String.empty(),
    @SerializedName("trackTimeMillis") val trackTimeMillis: Int,
    @SerializedName("collectionId") val albumId: String?,
    @SerializedName("collectionName") val albumName: String?,
    @SerializedName("artistId") val artistId: Int,
    @SerializedName("artistName") val artistName: String,
    @SerializedName("kind") val kind: String?,
    @SerializedName("artworkUrl100") val photo: String
)

fun MediaRemoteModel.toDomainModel() = MediaDomainModel(songId, photo, songName, albumName, artistName, trackTimeMillis)
