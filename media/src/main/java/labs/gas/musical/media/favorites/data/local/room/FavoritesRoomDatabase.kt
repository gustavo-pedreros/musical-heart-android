package labs.gas.musical.media.favorites.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import labs.gas.musical.media.favorites.data.local.room.model.Constants.DATABASE_NAME
import labs.gas.musical.media.favorites.data.local.room.model.MediaRoom

@Database(entities = [MediaRoom::class], version = 1, exportSchema = false)
abstract class FavoritesRoomDatabase : RoomDatabase() {
    abstract fun favoritesMediaDao(): FavoritesMediaDao

    companion object {
        fun build(context: Context) = Room.databaseBuilder(context, FavoritesRoomDatabase::class.java, DATABASE_NAME).build()
    }
}
