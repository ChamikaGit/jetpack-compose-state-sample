package com.chami.statejetpackcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chami.statejetpackcomposeapplication.ui.theme.StateJetpackComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModel<MyViewModel>()

            StateJetpackComposeApplicationTheme {
                //This type called Unidirectional data flow
                //We send the data from caller to it composable function
                //and we get the events from composable function to it's caller

//                var count by remember { mutableStateOf(0) }

                //if we want to preserve our state from android configuration changes,
                //1) we can use rememberSavable{} block
                // var count by rememberSaveable{mutableStateOf(0)}
                //2) we can use out viewModel instance instead of above implementation to preserve the state
                val count = viewModel.count

                ButtonUI(count) {
//                    count = it + 1
                    viewModel.increaseCount()
                }
            }
        }
    }
}

/** 1)This is not the best way to handle the state(using a global variable)
 * 2)Normally and best practice is write the compose in a separate file and
 * because composable function working dependently
 * 3)Don't use the global state it's not recommended.
 */
//val count = mutableStateOf(true)


@Composable
fun ButtonUI(currentCount: Int, updateCurrentCount: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                updateCurrentCount(currentCount)

            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.elevation(10.dp),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(text = "My Count $currentCount", fontSize = 17.sp)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateJetpackComposeApplicationTheme {
        ButtonUI(10) {

        }
    }
}