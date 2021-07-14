package labs.gas.musical.media.favorites.di

import android.content.Context
import dagger.Module
import dagger.Provides
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.media.favorites.data.FavoritesDataRepository
import labs.gas.musical.media.favorites.data.local.FavoritesLocalDatasource
import labs.gas.musical.media.favorites.data.local.room.FavoritesMediaDao
import labs.gas.musical.media.favorites.data.local.room.FavoritesRoomDatabase
import labs.gas.musical.media.favorites.data.local.room.FavoritesRoomDatasource
import labs.gas.musical.media.favorites.domain.AddFavoriteUseCase
import labs.gas.musical.media.favorites.domain.DeleteFavoriteUseCase
import labs.gas.musical.media.favorites.domain.FavoriteListUseCase
import labs.gas.musical.media.favorites.domain.FavoritesRepository
import javax.inject.Singleton

@Module
class FavoritesModule {
    @Provides
    fun provideFavoritesRepository(favoritesLocalDatasource: FavoritesLocalDatasource): FavoritesRepository {
        return FavoritesDataRepository(favoritesLocalDatasource)
    }

    @Provides
    fun provideSaveMediaUseCase(favoritesRepository: FavoritesRepository, scheduler: Scheduler): AddFavoriteUseCase {
        return AddFavoriteUseCase(favoritesRepository, scheduler)
    }

    @Provides
    fun provideDeleteMediaUseCase(favoritesRepository: FavoritesRepository, scheduler: Scheduler): DeleteFavoriteUseCase {
        return DeleteFavoriteUseCase(favoritesRepository, scheduler)
    }

    @Provides
    fun provideFavoritesListUseCase(favoritesRepository: FavoritesRepository, scheduler: Scheduler): FavoriteListUseCase {
        return FavoriteListUseCase(favoritesRepository, scheduler)
    }
}

@Module
class FavoritesLocalSourceModule {
    @Singleton
    @Provides
    fun provideFavoritesDatabase(context: Context) = FavoritesRoomDatabase.build(context)

    @Singleton
    @Provides
    fun provideFavoritesDao(favoritesRoomDatabase: FavoritesRoomDatabase) = favoritesRoomDatabase.favoritesMediaDao()

    @Singleton
    @Provides
    fun provideFavoritesLocalDatasource(favoritesMediaDao: FavoritesMediaDao): FavoritesLocalDatasource {
        return FavoritesRoomDatasource(favoritesMediaDao)
    }
}
