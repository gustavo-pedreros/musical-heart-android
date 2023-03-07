package labs.gas.musical.media.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import labs.gas.musical.core.networking.NetworkClient
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.media.favorites.data.local.FavoritesLocalDatasource
import labs.gas.musical.media.favorites.di.FavoritesLocalSourceModule
import labs.gas.musical.media.search.data.MediaDataRepository
import labs.gas.musical.media.search.data.remote.MediaRemoteDatasource
import labs.gas.musical.media.search.data.remote.itunes.Constants.BASE_URL
import labs.gas.musical.media.search.data.remote.itunes.ItunesDatasource
import labs.gas.musical.media.search.data.remote.itunes.ItunesService
import labs.gas.musical.media.search.domain.MediaRepository
import labs.gas.musical.media.search.domain.SearchMediaUseCase

@InstallIn(ViewModelComponent::class)
@Module(includes = [ItunesNetworkClientModule::class, FavoritesLocalSourceModule::class])
class MediaModule {
    @Provides
    fun provideMediaRemoteDatasource(itunesService: ItunesService): MediaRemoteDatasource = ItunesDatasource(itunesService)

    @Provides
    fun provideMediaRepository(
        remoteDatasource: MediaRemoteDatasource,
        favoritesLocalDatasource: FavoritesLocalDatasource
    ): MediaRepository = MediaDataRepository(remoteDatasource, favoritesLocalDatasource)

    @Provides
    fun provideSearchMediaUseCase(mediaRepository: MediaRepository, scheduler: Scheduler) = SearchMediaUseCase(mediaRepository, scheduler)
}

@InstallIn(ViewModelComponent::class)
@Module
class ItunesNetworkClientModule {
    @Provides
    fun provideItunesNetworkClient(): NetworkClient = NetworkClient(BASE_URL)

    @Provides
    fun provideItunesService(networkClient: NetworkClient): ItunesService {
        return networkClient.getClient(ItunesService::class.java)
    }
}
