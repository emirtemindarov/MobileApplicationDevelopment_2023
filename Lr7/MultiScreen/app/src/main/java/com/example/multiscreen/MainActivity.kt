package com.example.multiscreen

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiscreen.ui.theme.MultiScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiScreenTheme {
                Program()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Program() {
    val islands = mapOf(
        stringResource(R.string.canari) to CuriliActivity::class.java,
        stringResource(R.string.curili) to CanariActivity::class.java,
        stringResource(R.string.maldivi) to MaldiviActivity::class.java,
        stringResource(R.string.filipini) to FilipiniActivity::class.java
    )
    // Заголовок
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = "Куда бы поехать в отпуск?",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
        }
        // Кнопки для перехода к активности
        islands.forEach { island ->
            val context = LocalContext.current
            val intent = Intent(context, island.value)
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        context.startActivity(intent)
                        Toast.makeText(context, "Вы выбрали ${island.key}", Toast.LENGTH_SHORT).show()
                    }
            ) {
                Text(
                    text = island.key,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp, 0.dp)
                )
            }
            Divider()
        }
    }
}