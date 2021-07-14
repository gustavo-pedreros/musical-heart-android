package labs.gas.musical.media.search.domain.model

class MediaDomainModel(
    val id: Int,
    val photo: String,
    val name: String?,
    val album: String?,
    val artist: String,
    val duration: Int
)
