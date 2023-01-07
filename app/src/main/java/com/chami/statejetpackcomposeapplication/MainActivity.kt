package com.chami.statejetpackcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chami.statejetpackcomposeapplication.ui.theme.StateJetpackComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateJetpackComposeApplicationTheme {
                ButtonUI()
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
fun ButtonUI() {
    //Need to wrap this remember block.
    //remember used as a guard against recomposition
    //If we not use remember block{}.During recomposition mutableStateOf value state going back to the initial state.
    //val count = remember { mutableStateOf(true) }
    //using kotlin delegate function we can change this structure and can do our code more conses
    var count by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
//                      count.value = count.value+1
                count = !count
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan,
                contentColor = Color.Black
            ), elevation = ButtonDefaults.elevation(10.dp),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.padding(
                bottom = if (count) {
                    10.dp
                } else {
                    50.dp
                }
            )
        ) {
            Text(text = "My Count ${count}", fontSize = 16.sp)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateJetpackComposeApplicationTheme {
        ButtonUI()
    }
}