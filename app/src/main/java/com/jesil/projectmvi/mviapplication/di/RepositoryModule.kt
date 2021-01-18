package com.jesil.projectmvi.mviapplication.di

import com.jesil.projectmvi.mviapplication.repository.MainRepository
import com.jesil.projectmvi.mviapplication.retrofit.BlogRetrofit
import com.jesil.projectmvi.mviapplication.retrofit.NetworkMapper
import com.jesil.projectmvi.mviapplication.room.BlogDao
import com.jesil.projectmvi.mviapplication.room.CacheMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) : MainRepository =

         MainRepository(
            blogDao = blogDao,
            blogRetrofit = blogRetrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper
        )
}