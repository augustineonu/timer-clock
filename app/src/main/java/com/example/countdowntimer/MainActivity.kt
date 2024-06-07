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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel



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
fun ShotClock(shotClockViewModel: ShotClockViewModel = viewModel()) {
    val timerValue by remember { derivedStateOf { shotClockViewModel.timerValue } }

    LaunchedEffect(Unit) {
        shotClockViewModel.startTimer()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = timerValue.toString(),
            fontSize = 48.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { shotClockViewModel.pauseTimer() }) {
                Text(text = "Pause")
            }
            Button(onClick = { shotClockViewModel.resetTimer() }) {
                Text(text = "Reset")
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // Add some space between the rows
        Button(onClick = { shotClockViewModel.startTimer() }) {
            Text(text = "Play")
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


class ShotClockViewModel : ViewModel() {
    var timerValue by mutableStateOf(24)
        private set

    private var timerJob: Job? = null

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (timerValue > 0) {
                delay(1000L)
                timerValue--
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun resetTimer() {
        pauseTimer()
        timerValue = 24
    }
}



@Preview(showBackground = true)
@Composable
fun ShotClockAppPreview() {
    ShotClockApp()
}