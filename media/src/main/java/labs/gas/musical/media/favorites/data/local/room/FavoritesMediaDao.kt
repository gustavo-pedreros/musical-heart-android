package labs.gas.musical.media.favorites.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import labs.gas.musical.media.favorites.data.local.room.model.Constants.TABLE_NAME
import labs.gas.musical.media.favorites.data.local.room.model.MediaRoom

@Dao
interface FavoritesMediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMedia(media: MediaRoom)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :mediaId")
    fun deleteMedia(mediaId: Int)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getMediaList(): Single<List<MediaRoom>>

    @Query("SELECT id FROM $TABLE_NAME")
    fun getMediaIdList(): Single<List<Int>>

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()
}
