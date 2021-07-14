package labs.gas.musical.heart.ui.media.model

import labs.gas.musical.media.search.domain.model.MediaDomainModel
import java.text.SimpleDateFormat
import java.util.*

data class Media(
    val id: Int,
    val photo: String,
    val name: String?,
    val album: String?,
    val artist: String,
    val duration: Int,
    var isFavorite: Boolean = false
)

fun MediaDomainModel.toPresentationModel() = Media(id, photo, name, album, artist, duration, isFavorite)

fun Media.toDomainModel() = MediaDomainModel(id, photo, name, album, artist, duration, isFavorite)

fun Media.durationFormat(): String {
    val time: Long = duration.toLong()
    return SimpleDateFormat("mm:ss").format(Date(time))
}
