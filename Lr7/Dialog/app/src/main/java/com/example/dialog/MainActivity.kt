package com.example.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogTheme {
                Program()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Program() {
    val context = LocalContext.current
    var currentBackgroundColor by remember { mutableStateOf(Color.White) }
    val colors = mapOf(
        stringResource(R.string.red) to colorResource(R.color.red),
        stringResource(R.string.yellow) to colorResource(R.color.yellow),
        stringResource(R.string.green) to colorResource(R.color.green)
    )
    var openDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Заголовок
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = "Dialog",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
        }
        // Блок фона с кнопкой
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(currentBackgroundColor)
        ) {
            // Кнопка перехода к диалоговому окну
            Button(
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color.LightGray),
                onClick = {
                    openDialog = true
                },
                enabled = !openDialog
            ) {
                Text("Выбрать фон", color = Color.Black)
            }
        }



        if (openDialog) {
            Dialog(
                onDismissRequest = { openDialog = false }
            ) {
                Column(
                    modifier = Modifier
                        .size(400.dp)
                        .background(Color.White)
                ) {
                    // Заголовок диалогового окна
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.change),
                            color = Color.Blue,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                    }
                    Divider(color = Color.Blue)
                    // Кнопки диалогового окна
                    colors.forEach { color ->
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                                .height(40.dp)
                                .clickable {
                                    currentBackgroundColor = color.value
                                    Toast
                                        .makeText(context, "Вы выбрали ${color.key}", Toast.LENGTH_SHORT)
                                        .show()
                                    openDialog = false
                                }
                        ) {
                            Text(
                                text = color.key,
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(16.dp, 0.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
        }

    }
}