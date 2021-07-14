package labs.gas.musical.media.search.di

import dagger.Module
import dagger.Provides
import labs.gas.musical.core.networking.NetworkClient
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.media.search.data.MediaDataRepository
import labs.gas.musical.media.search.data.remote.MediaRemoteDatasource
import labs.gas.musical.media.search.data.remote.itunes.Constants.BASE_URL
import labs.gas.musical.media.search.data.remote.itunes.ItunesDatasource
import labs.gas.musical.media.search.data.remote.itunes.ItunesService
import labs.gas.musical.media.search.domain.MediaRepository
import labs.gas.musical.media.search.domain.SearchMediaUseCase

@Module(includes = [ItunesNetworkClientModule::class])
class MediaModule {
    @Provides
    fun provideMediaRemoteDatasource(itunesService: ItunesService): MediaRemoteDatasource = ItunesDatasource(itunesService)

    @Provides
    fun provideMediaRepository(remoteDatasource: MediaRemoteDatasource): MediaRepository = MediaDataRepository(remoteDatasource)

    @Provides
    fun provideSearchMediaUseCase(mediaRepository: MediaRepository, scheduler: Scheduler) = SearchMediaUseCase(mediaRepository, scheduler)
}

@Module
class ItunesNetworkClientModule {
    @Provides
    fun provideItunesNetworkClient(): NetworkClient = NetworkClient(BASE_URL)

    @Provides
    fun provideItunesService(networkClient: NetworkClient): ItunesService {
        return networkClient.getClient(ItunesService::class.java)
    }
}
