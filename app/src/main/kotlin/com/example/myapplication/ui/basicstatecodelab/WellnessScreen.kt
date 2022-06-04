package com.example.myapplication.ui.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    AttemptFixTemp(modifier)

}

@Composable // STATE HOIST ATTEMPT
fun AttemptFixTemp(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableStateOf(0) }
    val onClick: (Int) -> Unit = { newCounter ->
        counter = newCounter
    }

    WaterCounter2(counter = counter, onClick = onClick)
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ }, modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = "You've had $count glasses.")
        }

        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text(text = "Add one")
        }
    }
}

@Composable
fun WaterCounter(
    modifier: Modifier = Modifier,
    counter: Int,
    onClick: (Int) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (counter > 0) {
            // This text is present if the button has been clicked.
            // at least once; absent otherwise.
            Text(text = "You've had $counter glasses.")
        }
        Button(
            onClick = { onClick(counter + 1) },
            Modifier.padding(top = 8.dp),
            enabled = counter < 10
        ) {
            Text(text = "Add one")
        }
    }
}

@Composable
fun WaterCounter2(
    modifier: Modifier = Modifier,
    counter: Int,
    onClick: (Int) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (counter > 0) {
            // This text is present if the button has been clicked.
            // at least once; absent otherwise.

            var showTask by remember { mutableStateOf(true) }
            var checkedState by remember { mutableStateOf(true)}

            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 walk today?",
                    checked = checkedState,
                    onCheckedChange = { newValue -> checkedState = newValue},
                    onClose = { showTask = false })
            }

            Text(text = "You've had $counter glasses.")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(
                onClick = { onClick(counter + 1) },
                enabled = counter < 10
            ) {
                Text(text = "Add one")
            }

            Button(
                onClick = { onClick(0) },
                Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Clear water count")
            }
        }
    }
}

