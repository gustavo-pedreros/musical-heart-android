package labs.gas.musical.heart.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import labs.gas.musical.heart.MusicalHeartApp
import labs.gas.musical.heart.ui.di.ActivityModule
import labs.gas.musical.media.favorites.di.FavoritesLocalSourceModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        SchedulerModule::class,
        FavoritesLocalSourceModule::class,
        ActivityModule::class
    ]
)
interface AppComponent {
    fun inject(app: MusicalHeartApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
