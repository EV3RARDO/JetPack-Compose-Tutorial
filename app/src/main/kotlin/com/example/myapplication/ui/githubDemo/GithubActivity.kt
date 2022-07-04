package com.example.myapplication.ui.githubDemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.myapplication.data.model.Repository
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewModel: RepositoriesViewModel = viewModel()
                //val repos = viewModel.repositories.value
                val reposFlow = viewModel.repositories
                val lazyRepoItems: LazyPagingItems<Repository> = reposFlow.collectAsLazyPagingItems()
                RepositoriesScreen(lazyRepoItems)
            }
        }
    }
}