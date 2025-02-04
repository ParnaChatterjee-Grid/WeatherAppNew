package com.example.presentation.viewmodels


import com.example.domain.usecase.GetCityUsecase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(getCityUsecase: GetCityUsecase): ViewModelfactory {
        return ViewModelfactory(getCityUsecase)
    }
}
