package com.example.myapplication.ui.intrinsicsizedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class IntrinsicSizeDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {

        var textState by remember { mutableStateOf("") }
        val onTextChange = { text: String -> textState = text }

        Column(
            Modifier
                .width(200.dp)
                .padding(5.dp)) {

            Column(Modifier.width(IntrinsicSize.Min)) {
                Text(text = textState, modifier = Modifier.padding(start = 4.dp))

                Box(modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(Color.Blue))
            }

            MyTextField(text = textState, onTextChange = onTextChange)
        }
    }

    @Composable
    fun MyTextField(text: String, onTextChange: (String) -> Unit) {
        TextField(value = text, onValueChange = onTextChange)

    }


}