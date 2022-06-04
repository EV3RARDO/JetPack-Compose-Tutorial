package com.example.myapplication.ui.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class PlaygroundActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp {
                HelloScreen()
            }
        }
    }
}

@Composable
fun Counter(count: Int, updateCounter: (Int) -> Unit) {

    Button(onClick = { updateCounter(count + 1) }) {
        Text(text = "I have been clicked $count times")
    }

}

@Composable
private fun ColumnNames(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

        items (items = names) {
            Greeting(name = it)
            Divider()
        }

    }
}

@Composable
fun myScreenContent(names: List<String> = List(1000) { "Hello Android $it"}) {

    var counterState by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {

        ColumnNames(names = names, modifier = Modifier.weight(1f))

        Counter(count = counterState, updateCounter = { newCounter ->
            counterState = newCounter
        })

        if (counterState >= 5) {
            Text(text = "I love to Count!")
        }
    }
}

@Composable
fun myApp(content: @Composable () -> Unit) {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {

    var isSelected by remember {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
        animationSpec = tween(4000)
    )

    Surface(color = targetColor) {
        Text(
            text = "$name!",
            modifier = Modifier
                .clickable { isSelected = !isSelected }
                .padding(16.dp)
        )


    }
}
@Composable
fun HelloScreen(viewModel: PlaygroundViewModel = viewModel(modelClass = PlaygroundViewModel::class.java)) {
    val name by viewModel.name.observeAsState("")
    HelloContent(name = name, onNameChange = { viewModel.onNameChange(it)} )

}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text =  if (name.isNotEmpty()) "Hello, $name!" else "Hello!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name")})

    }

}

@Preview(fontScale = 1.2f)
@Composable
fun DefaultPreview() {
    myApp {
        HelloScreen()
    }
}