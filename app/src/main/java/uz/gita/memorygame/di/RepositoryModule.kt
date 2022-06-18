package uz.gita.memorygame.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.memorygame.domain.AppRepository
import uz.gita.memorygame.domain.impls.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun  getRepository(impl: AppRepositoryImpl) : AppRepository
}