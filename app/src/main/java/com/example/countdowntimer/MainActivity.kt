package com.example.countdowntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countdowntimer.ui.theme.CountdownTimerTheme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountdownTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShotClockApp()
                }
            }
        }
    }
}

@Composable
fun ShotClockApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ShotClock()
                Spacer(modifier = Modifier.height(40.dp))
                ShotClock()
                Spacer(modifier = Modifier.height(40.dp))
                ShowText()
            }
        }
    }
}

@Composable
fun ShotClock() {
    var timerValue by remember { mutableStateOf("24") } // Placeholder value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = timerValue,
            fontSize = 48.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { /* Pause timer logic */ }) {
                Text(text = "Pause")
            }
            Button(onClick = { /* Reset timer logic */ }) {
                Text(text = "Reset")
            }
        }
    }
}

@Composable
fun ShowText() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "A basketball field has 2 shot clocks, one above each rim. Both countdown 24 seconds.\n" +
                    "        Referees can stop and/or reset clocks when needed.",
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "As a user, I want to see 2 clocks with pause & reset buttons, so I can manage the game.",
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ShotClockAppPreview() {
//    ShotClockApp()
//}