package com.example.myapplication.ui.animatevisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class AnimationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }


    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun MainScreen() {
        var boxVisible by remember { mutableStateOf(true) }

        val onClick = { newState: Boolean ->
            boxVisible = newState
        }

        Column(
            Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton(text = "Show", targetState = true, onClick = onClick)
                CustomButton(text = "Hide", targetState = false, onClick = onClick)
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (boxVisible) {
                AnimatedVisibility(
                    visible = boxVisible,
                    enter = fadeIn(
                        animationSpec = repeatable(
                            10, animation = tween(durationMillis = 2000),
                            repeatMode = RepeatMode.Reverse
                        )
                    ),
                    exit = slideOutVertically()
                ) {

                    Row {
                        Box(
                            modifier = Modifier
                                .size(height = 150.dp, width = 150.dp)
                                .background(Color.Blue)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Box(
                            Modifier
                                .animateEnterExit(
                                    enter = slideInVertically(
                                        animationSpec = tween(durationMillis = 5500)
                                    ),
                                    exit = slideOutVertically(animationSpec = tween(durationMillis = 5500))
                                )
                                .size(width = 150.dp, height = 150.dp)
                                .background(Color.Red)
                        )
                    }
                }
            }
        }

    }

    @Composable
    fun CustomButton(
        text: String,
        targetState: Boolean,
        onClick: (Boolean) -> Unit,
        bgColor: Color = Color.Blue
    ) {
        Button(
            onClick = { onClick(targetState) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = bgColor,
                contentColor = Color.White
            )
        ) {
            Text(text = text)
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            MainScreen()
        }

    }


}