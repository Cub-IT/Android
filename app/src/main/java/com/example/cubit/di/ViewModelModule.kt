package com.example.cubit.di

import com.example.core.data.repository.AuthRepository
import com.example.feature_user.presentation.sign_in.SignInViewModel
import com.example.feature_user.presentation.sign_up.SignUpViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    /*@Provides
    fun provideSignInViewModel(authRepository: AuthRepository): SignInViewModel {
        return SignInViewModel(
            onSignInClicked = {},
            onSignUpClicked = {},
            authRepository = authRepository
        )
    }*/

    @Provides
    fun provideSignUpViewModel(authRepository: AuthRepository): SignUpViewModel {
        return SignUpViewModel(
            onSignInClicked = {},
            onSignUpClicked = {},
            authRepository = authRepository
        )
    }

}