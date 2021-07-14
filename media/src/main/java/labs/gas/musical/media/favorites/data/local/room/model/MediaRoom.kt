package labs.gas.musical.media.favorites.data.local.room.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import labs.gas.musical.media.favorites.data.local.room.model.Constants.TABLE_NAME

import labs.gas.musical.media.search.domain.model.MediaDomainModel

@Entity(tableName = TABLE_NAME, indices = [Index(value = ["id"], unique = true)])
class MediaRoom(
    @PrimaryKey val id: Int,
    val photo: String,
    val name: String?,
    val album: String?,
    val artist: String,
    val duration: Int
)

fun MediaDomainModel.toRoomModel() = MediaRoom(id, photo, name, album, artist, duration)

fun MediaRoom.toDomainModel() = MediaDomainModel(id, photo, name, album, artist, duration, true)
