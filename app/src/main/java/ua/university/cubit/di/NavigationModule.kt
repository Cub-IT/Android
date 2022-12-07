package ua.university.cubit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.university.navigation.navigator.ComposeNavigation
import ua.university.navigation.navigator.graph.Auth
import ua.university.navigation.navigator.graph.Group
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Singleton
    @Provides
    fun provideComposeNavigation(
        auth: Auth,
        group: Group
    ): ComposeNavigation {
        return ComposeNavigation(auth, group)
    }
}