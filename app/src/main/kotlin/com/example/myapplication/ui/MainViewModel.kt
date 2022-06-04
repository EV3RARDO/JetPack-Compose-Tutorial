package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.interactor.GetGithubUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubUsersUseCase: GetGithubUsersUseCase
): ViewModel() {




}