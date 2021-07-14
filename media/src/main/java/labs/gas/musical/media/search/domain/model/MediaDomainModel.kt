package labs.gas.musical.media.search.domain.model

import labs.gas.musical.media.favorites.data.local.room.model.MediaRoom

class MediaDomainModel(
    val id: Int,
    val photo: String,
    val name: String?,
    val album: String?,
    val artist: String,
    val duration: Int,
    var isFavorite: Boolean = false
)

fun MediaDomainModel.toRoom() = MediaRoom(id, photo, name, album, artist, duration)
