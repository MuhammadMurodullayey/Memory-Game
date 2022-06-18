package uz.gita.memorygame.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygame.domain.AppRepository
import uz.gita.memorygame.domain.impls.AppRepositoryImpl
import uz.gita.memorygame.usecase.AllDataUseCase
import uz.gita.memorygame.usecase.impl.AllDataUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @[Binds Singleton]
    fun getUseCase(impl:AllDataUseCaseImpl) : AllDataUseCase
}