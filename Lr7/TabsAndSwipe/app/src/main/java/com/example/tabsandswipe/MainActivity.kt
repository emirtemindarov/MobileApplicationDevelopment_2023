package com.example.tabsandswipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tabsandswipe.ui.theme.TabsAndSwipeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabsAndSwipeTheme {
                Program()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Program() {
    Column(modifier = Modifier.fillMaxSize())
    {
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
                text = "Tabs and Swipe",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
        }

        // Вкладки
        val tabs = listOf(
            stringResource(R.string.lenta),
            stringResource(R.string.photo),
            stringResource(R.string.map)
        )
        var currentTabIndex by remember { mutableStateOf(0) }
        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = currentTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title, color = Color.White) },
                        selected = currentTabIndex == index,
                        onClick = { currentTabIndex = index },
                        modifier = Modifier.background(Color.Blue)
                    )
                }
            }
            when (currentTabIndex) {
                0 -> LentaScreen()
                1 -> PhotoScreen()
                2 -> MapScreen()
            }
        }
    }
}