package com.example.myapplication.ui.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier
) {

}


@Composable
private fun getWellnessTasks(): List<WellnessTask> = List(30) { i -> WellnessTask(i, "Task # $i") }