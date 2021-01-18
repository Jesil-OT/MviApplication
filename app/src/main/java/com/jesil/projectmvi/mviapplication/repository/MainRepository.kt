package com.jesil.projectmvi.mviapplication.repository

import com.jesil.projectmvi.mviapplication.model.Blog
import com.jesil.projectmvi.mviapplication.retrofit.BlogRetrofit
import com.jesil.projectmvi.mviapplication.retrofit.NetworkMapper
import com.jesil.projectmvi.mviapplication.room.BlogDao
import com.jesil.projectmvi.mviapplication.room.CacheMapper
import com.jesil.projectmvi.mviapplication.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.getBlog()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }
        catch (e : Exception){
            emit(DataState.Error(exception = e))
        }
    }
}