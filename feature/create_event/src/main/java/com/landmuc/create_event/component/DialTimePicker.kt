package com.landmuc.create_event.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialTimerPicker(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val now = Clock.System.now()

    val timePickerState = rememberTimePickerState(
        initialHour = now.toLocalDateTime(TimeZone.currentSystemDefault()).hour,
        initialMinute = now.toLocalDateTime(TimeZone.currentSystemDefault()).minute,
        is24Hour = true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeInput(
            state = timePickerState,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
            Button(onClick = { onConfirm(timePickerState) }) {
                Text(text = "Confirm selection")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDialTimePicker() {
    DialTimerPicker(
        onConfirm = {},
        onDismiss = {}
        )
}