package com.example.myapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.model.Repository
import com.example.myapplication.data.services.RepositoriesApiService
import javax.inject.Inject

class RepositoriesPagingSource @Inject constructor(
    private val repositoriesApiService: RepositoriesApiService
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val nextPageIndex = params.key ?: 1
            val repos = repositoriesApiService.getRepositories(nextPageIndex).repos
            LoadResult.Page(
                data = repos,
                prevKey = if (nextPageIndex == 1) null else nextPageIndex - 1,
                nextKey  = nextPageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

