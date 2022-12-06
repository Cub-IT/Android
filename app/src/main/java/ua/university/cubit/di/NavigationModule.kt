package ua.university.cubit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.university.navigation.navigator.ComposeNavigation
import ua.university.navigation.navigator.graph.Auth

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    fun provideComposeNavigation(
        auth: Auth
    ): ComposeNavigation {
        return ComposeNavigation(auth)
    }
}