package com.example.lr6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lr6.ui.theme.Lr6Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lr6Theme {
                Program2()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Program2() {
    val START = stringResource(R.string.start)
    val CHECK = stringResource(R.string.check)
    val AHEAD = stringResource(R.string.ahead)
    val BEHIND = stringResource(R.string.behind)
    val HIT: String = stringResource(R.string.hit)
    val RESTART = stringResource(R.string.restart)
    val ERROR = stringResource(R.string.error)

    Column {
        var randomValue by remember { mutableStateOf(setNewRandomValue()) }
        var topText by remember { mutableStateOf(START) }
        Text(text = topText)

        var inputValue by remember { mutableStateOf("") }
        val textfield = OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = processInput(it) }
        )
        Button(
            onClick = {
                if (topText == HIT) {
                    topText = START
                    randomValue = setNewRandomValue()
                    inputValue = ""
                } else {
                    try {
                        topText = when (PressButton(inputValue.toInt(), randomValue)) {
                            "ahead" -> AHEAD
                            "behind" -> BEHIND
                            else -> HIT
                        }
                    } catch (e: NumberFormatException) {
                        topText = ERROR
                    }
                }
            }
        ) {
            Text(
                text = if (topText == HIT) {
                    RESTART
                } else {
                    CHECK
                }
            )
        }
    }
}

fun setNewRandomValue(): Int {
    return Random.nextInt(1, 101)
}

fun processInput(input: String): String {
    return input.replace("\n", "")
}

fun PressButton(inputValue: Int, randomValue: Int): String {
    if (inputValue > randomValue) {
        return "ahead"
    } else if (inputValue < randomValue) {
        return "behind"
    } else {
        return "hit"
    }
}
