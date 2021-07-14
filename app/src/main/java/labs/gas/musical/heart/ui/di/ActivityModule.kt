package labs.gas.musical.heart.ui.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import labs.gas.musical.core.extensions.viewModelProvidersOf
import labs.gas.musical.heart.ui.MusicalHeartActivity
import labs.gas.musical.heart.ui.favorites.FavoritesFragment
import labs.gas.musical.heart.ui.favorites.FavoritesViewModel
import labs.gas.musical.heart.ui.favorites.FavoritesViewModelFactory
import labs.gas.musical.heart.ui.media.MediaFragment
import labs.gas.musical.heart.ui.media.MediaViewModel
import labs.gas.musical.heart.ui.media.MediaViewModelFactory
import labs.gas.musical.media.favorites.di.FavoritesModule
import labs.gas.musical.media.favorites.domain.AddFavoriteUseCase
import labs.gas.musical.media.favorites.domain.DeleteFavoriteUseCase
import labs.gas.musical.media.favorites.domain.FavoriteListUseCase
import labs.gas.musical.media.search.di.MediaModule
import labs.gas.musical.media.search.domain.SearchMediaUseCase

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(
        modules = [
            MediaActivitiesModule::class,
            FragmentBinder::class,
            MediaModule::class,
            FavoritesModule::class
        ]
    )
    abstract fun bindMusicalHeartActivity(): MusicalHeartActivity
}

@Module
abstract class FragmentBinder {
    @ContributesAndroidInjector
    abstract fun bindMeidaFragment(): MediaFragment

    @ContributesAndroidInjector
    abstract fun bindFavoritesFragment(): FavoritesFragment
}

@Module
class MediaActivitiesModule {
    //Media
    @Provides
    fun provideMediaViewModelFactory(mediaUseCase: SearchMediaUseCase) = MediaViewModelFactory(mediaUseCase)

    @Provides
    fun provideMediaViewModel(activity: MusicalHeartActivity, viewModelFactory: MediaViewModelFactory) =
        activity.viewModelProvidersOf<MediaViewModel>(viewModelFactory)

    // Favorites
    @Provides
    fun provideFavoriteViewModelFactory(
        addFavoriteUseCase: AddFavoriteUseCase,
        deleteFavoriteUseCase: DeleteFavoriteUseCase,
        favoriteListUseCase: FavoriteListUseCase
    ) = FavoritesViewModelFactory(addFavoriteUseCase, deleteFavoriteUseCase, favoriteListUseCase)

    @Provides
    fun provideFavoriteViewModel(activity: MusicalHeartActivity, viewModelFactory: FavoritesViewModelFactory) =
        activity.viewModelProvidersOf<FavoritesViewModel>(viewModelFactory)
}
