package com.example.myapplication.ui.contrainstDemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

class ConstraintActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestConstraintSets()
        }
    }

    @Composable
    fun MainScreen() {
        ConstraintLayout(
            Modifier
                .size(width = 200.dp, height = 200.dp)
                .background(Color.Magenta)
        ) {
            val (button1, button2, button3) = createRefs()

            MyButton(text = "Button1", Modifier.constrainAs(button1) {
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
                bottom.linkTo(button2.top)

            })

            MyButton(text = "Button2", Modifier.constrainAs(button2) {
                centerHorizontallyTo(parent)
                top.linkTo(button1.bottom)
                bottom.linkTo(parent.bottom)

            })
        }
    }

    @Composable
    fun MyButton(text: String, modifier: Modifier = Modifier) {
        Button(onClick = {}, modifier = modifier) {
            Text(text = text)
        }
    }
    
    private fun myConstrainSet(margin: Dp): ConstraintSet {
        return ConstraintSet {
            val button1  = createRefFor("button1")
            
            constrain(button1) {
                linkTo(parent.top, parent.bottom, topMargin = margin, bottomMargin = margin)
                linkTo(parent.start, parent.end, startMargin = margin, endMargin = margin)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            
        }
    }

    @Composable
    fun TestConstraintSets() {
        ConstraintLayout(
            Modifier
                .size(width = 200.dp, height = 200.dp)
                .background(Color.Yellow)) {
            val constraints = myConstrainSet(margin = 8.dp)
            MyButton(text = "Button1",
                Modifier
                    .size(200.dp)
                    .layoutId("button1"))
            //val button1 = createRef()

/*            MyButton(text = "Button1",
                Modifier
                    .size(200.dp)
                    .constrainAs(button1) {
                        linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
                        linkTo(parent.start, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints

                    })*/

        }

    }

    @Composable
    fun TestBarrier() {

        ConstraintLayout(
            Modifier
                .size(width = 350.dp, height = 220.dp)
                .background(Color.Cyan)
        ) {

            val (button1, button2, button3) = createRefs()
            val barrier = createEndBarrier(button1, button2)

            MyButton(text = "Button1",
                Modifier
                    .width(100.dp)
                    .constrainAs(button1) {
                        top.linkTo(parent.top, margin = 30.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                    })

            MyButton(text = "Button2",
                Modifier
                    .width(150.dp)
                    .constrainAs(button2) {
                        top.linkTo(button1.bottom, margin = 20.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                    })

            MyButton(text = "Button3",
                Modifier
                    .width(100.dp)
                    .constrainAs(button3) {
                        linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
                        linkTo(button1.end, parent.end, startMargin = 30.dp, endMargin = 8.dp)
                        start.linkTo(barrier, margin = 30.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    })
        }

    }

    @Composable
    fun TestGuideline() {
        ConstraintLayout(
            Modifier
                .size(width = 400.dp, height = 220.dp)
                .background(Color.Cyan)
        ) {

            val (button1, button2, button3) = createRefs()

            val guide = createGuidelineFromStart(fraction = .60f)

            MyButton(text = "Button1", Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 30.dp)
                end.linkTo(guide)
            })

            MyButton(text = "Button2", Modifier.constrainAs(button2) {
                top.linkTo(button1.bottom)
                start.linkTo(guide)
            })

            MyButton(text = "Button3", Modifier.constrainAs(button3) {
                top.linkTo(button2.bottom)
                end.linkTo(guide)
            })
        }
    }

    @Composable
    fun ChainTest() {
        ConstraintLayout(
            Modifier
                .size(width = 400.dp, height = 100.dp)
                .background(Color.Green)
        ) {

            val (button1, button2, button3) = createRefs()

            createHorizontalChain(button1, button2, button3, chainStyle = ChainStyle.Spread)

            MyButton(text = "Button1", Modifier.constrainAs(button1) {
                centerVerticallyTo(parent)
            })

            MyButton(text = "Button2", Modifier.constrainAs(button2) {
                centerVerticallyTo(parent)
            })

            MyButton(text = "Button3", Modifier.constrainAs(button3) {
                centerVerticallyTo(parent)
            })
        }
    }

    @Composable
    fun TestConstraintLayout() {
        ConstraintLayout(
            Modifier
                .size(width = 200.dp, height = 300.dp)
                .background(Color.Green)
        ) {

            val text1 = createRef()

            Text(text = "Hello", modifier = Modifier.constrainAs(text1) {
                // constraints here
                top.linkTo(parent.top, 45.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                // start.linkTo(parent.start, 80.dp)
            })
        }
    }
}