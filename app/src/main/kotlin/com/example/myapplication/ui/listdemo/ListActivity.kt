package com.example.myapplication.ui.listdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class ListActivity : ComponentActivity() {

    val phones = listOf(
        "Apple iPhone 12", "Google Pixel 4", "Google Pixel 6",
        "Samsung Galaxy 6s", "Apple iPhone 7", "OnePlus 7", "OnePlus 9 Pro",
        "Apple iPhone 13", "Samsung Galaxy Z Flip", "Google Pixel 4a",
        "Apple iPhone 8"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }

    }

    @Composable
    fun MainScreen() {
        RowList()
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }

    @Composable
    fun RowList() {
        val scrollState = rememberScrollState()
        Row(Modifier.horizontalScroll(scrollState)) {

            repeat(50) {
                Text(" $it ", style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(5.dp))
            }

        }

    }


    @Composable
    fun ColumnList() {

        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        Column {
            Row {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(0)
                        }

                    },
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(2.dp)
                ) {
                    Text(text = "Top")

                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                    },
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(2.dp)
                ) {
                    Text(text = "End")

                }
            }

            Column(Modifier.verticalScroll(scrollState)) {
                repeat(500) {
                    Text(
                        text = "List item $it",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }


    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun TestLazyVerticalGrid() {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(10.dp)
        ) {

            items(45) { index ->
                Card(
                    backgroundColor = Color.Blue,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "$index",
                        fontSize = 35.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

            }

        }

    }
}