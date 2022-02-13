package com.example.epayapplication.ui.gallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.epayapplication.api.ApiInterface
import com.example.epayapplication.models.images.Photo
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val apiInterface: ApiInterface,
    private val query: String
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = apiInterface.searchPhotos(query, position, params.loadSize)
            val photos = response.photos.photo

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return null
    }
}