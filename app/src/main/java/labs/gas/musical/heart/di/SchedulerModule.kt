package labs.gas.musical.heart.di

import dagger.Module
import dagger.Provides
import labs.gas.musical.core.threads.Scheduler
import labs.gas.musical.core.threads.SchedulerProvider

@Module
class SchedulerModule {
    @Provides
    fun provideScheduler(): Scheduler = SchedulerProvider()
}
