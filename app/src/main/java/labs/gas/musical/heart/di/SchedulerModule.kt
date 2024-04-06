package labs.gas.musical.heart.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.core.threads.SchedulerProvider

@Module
@InstallIn(SingletonComponent::class)
class SchedulerModule {
    @Provides
    fun provideScheduler(): Scheduler = SchedulerProvider()
}
