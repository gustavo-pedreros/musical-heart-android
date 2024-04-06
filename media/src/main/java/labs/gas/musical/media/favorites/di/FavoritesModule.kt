package labs.gas.musical.media.favorites.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.media.favorites.data.FavoritesDataRepository
import labs.gas.musical.media.favorites.data.local.FavoritesLocalDatasource
import labs.gas.musical.media.favorites.data.local.room.FavoritesMediaDao
import labs.gas.musical.media.favorites.data.local.room.FavoritesRoomDatabase
import labs.gas.musical.media.favorites.data.local.room.FavoritesRoomDatasource
import labs.gas.musical.media.favorites.domain.usecase.AddFavoriteUseCase
import labs.gas.musical.media.favorites.domain.usecase.DeleteFavoriteUseCase
import labs.gas.musical.media.favorites.domain.usecase.FavoriteListUseCase
import labs.gas.musical.media.favorites.domain.FavoritesRepository

@InstallIn(ViewModelComponent::class)
@Module(includes = [FavoritesLocalSourceModule::class])
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

@InstallIn(ViewModelComponent::class)
@Module
class FavoritesLocalSourceModule {
    @Provides
    fun provideFavoritesDatabase(context: Context) = FavoritesRoomDatabase.build(context)

    @Provides
    fun provideFavoritesDao(favoritesRoomDatabase: FavoritesRoomDatabase) = favoritesRoomDatabase.favoritesMediaDao()

    @Provides
    fun provideFavoritesLocalDatasource(favoritesMediaDao: FavoritesMediaDao): FavoritesLocalDatasource {
        return FavoritesRoomDatasource(favoritesMediaDao)
    }
}
