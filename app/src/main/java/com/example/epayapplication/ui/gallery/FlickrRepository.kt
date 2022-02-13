package com.example.epayapplication.ui.gallery

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.epayapplication.api.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(apiInterface, query) }
        ).liveData
}