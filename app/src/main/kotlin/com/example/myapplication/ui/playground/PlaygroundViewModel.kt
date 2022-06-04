package com.example.myapplication.ui.playground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaygroundViewModel: ViewModel() {

    private var _name: MutableLiveData<String> = MutableLiveData("")
    var name: LiveData<String> = _name

    fun onNameChange(newName: String) {
        _name.value = newName
    }
}