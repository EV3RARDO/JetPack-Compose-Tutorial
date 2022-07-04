package com.example.myapplication.ui.githubDemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.RepositoriesPagingSource
import com.example.myapplication.data.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val reposPagingSource: RepositoriesPagingSource
) : ViewModel() {


    val repositories: Flow<PagingData<Repository>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            reposPagingSource
        }
    ).flow.cachedIn(viewModelScope)

}


