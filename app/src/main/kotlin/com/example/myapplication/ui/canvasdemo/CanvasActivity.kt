package com.example.myapplication.ui.canvasdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

class CanvasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainScreen() }
    }
}

@Composable
fun MainScreen() {
    DrawOval()
}


@Composable
fun DrawOval() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.Yellow)
    ) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawOval(
            color = Color.Blue,
            topLeft = Offset(x = 25.dp.toPx(), y = 90.dp.toPx()),
            size = Size(
                width = canvasHeight - 50.dp.toPx(),
                height = canvasHeight / 2 - 50.dp.toPx()
            ),
            style = Stroke(width = 12.dp.toPx())
        )

    }
}

@Composable
fun DrawCircle() {

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.Yellow)
    ) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawCircle(
            color = Color.Blue,
            center = center,
            radius = 120.dp.toPx()
        )

    }
}


@Composable
fun DrawRect() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.Yellow)
    ) {

        rotate(45f) {
            drawRect(color = Color.Blue, topLeft = Offset(200f, 200f), size = size / 2f)
        }

/*        val size = Size(
            width = 280.dp.toPx(),
            height = 200.dp.toPx()
        )

        drawRoundRect(
            color = Color.Blue,
            size = size,
            topLeft = Offset(20f, 20f),
            style = Stroke(width = 8.dp.toPx()),
            cornerRadius = CornerRadius(x = 30.dp.toPx(), y = 30.dp.toPx())
        )*/
    }
}

@Composable
fun DrawLine() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.Yellow)
    ) {
        val height = size.height
        val width = size.width

        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = width, y = height),
            color = Color.Blue,
            strokeWidth = 16.0f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 10f, 10f, 10f), phase = 0f)
        )
    }
}