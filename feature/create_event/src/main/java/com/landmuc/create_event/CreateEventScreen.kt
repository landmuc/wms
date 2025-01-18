@file:OptIn(ExperimentalMaterial3Api::class)

package com.landmuc.create_event

import DialTimerPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.landmuc.create_event.component.DateRangePickerModal
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.material3.TimePickerState
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen(
    onBackClick: () -> Unit,
    viewModel: CreateEventViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val eventTitle by viewModel.eventTitle.collectAsState()
    val eventDescription by viewModel.eventDescription.collectAsState()
    val eventDate by viewModel.eventDate.collectAsState()
    val eventTime by viewModel.eventTime.collectAsState()
    val eventEndDate by viewModel.eventEndDate.collectAsState()
    val eventEndTime by viewModel.eventEndTime.collectAsState()
    val showDateRangePicker by viewModel.showDateRangePicker.collectAsState()
    val showTimePicker by viewModel.showTimePicker.collectAsState()
    val showEndTimePicker by viewModel.showEndTimePicker.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Create New Event",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF96CCF8)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Title Section
                    Column {
                        Text(
                            text = "Event Title",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = eventTitle,
                            onValueChange = viewModel::onEventTitleChanged,
                            label = { Text("Enter event title") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // Date Section
                    Column {
                        Text(
                            text = "Event Date",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedTextField(
                                value = "$eventDate - $eventEndDate",
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier.weight(1f),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Date Range"
                                    )
                                }
                            )
                            IconButton(
                                onClick = { viewModel.onShowDateRangePickerChanged() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Select Date Range"
                                )
                            }
                        }
                    }

                    // Time Section
                    Column {
                        Text(
                            text = "Event Time",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = "${eventTime.hour}:${eventTime.minute}",
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier.weight(1f),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Schedule,
                                        contentDescription = "Start Time"
                                    )
                                }
                            )
                            Text(
                                text = "to",
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            OutlinedTextField(
                                value = "${eventEndTime.hour}:${eventEndTime.minute}",
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier.weight(1f),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Schedule,
                                        contentDescription = "End Time"
                                    )
                                }
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = {
                                    viewModel.onShowTimePickerChanged()
                                    viewModel.onShowEndTimePickerChanged(false)
                                }
                            ) {
                                Text("Set Start Time")
                            }
                            TextButton(
                                onClick = {
                                    viewModel.onShowEndTimePickerChanged()
                                    viewModel.onShowTimePickerChanged(false)
                                }
                            ) {
                                Text("Set End Time")
                            }
                        }
                    }

                    // Description Section
                    Column {
                        Text(
                            text = "Event Description",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = eventDescription,
                            onValueChange = viewModel::onEventDescriptionChanged,
                            label = { Text("Describe your event") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            maxLines = 5
                        )
                    }
                }
            }

            // Create Button
            Button(
                onClick = {
                    viewModel.sendCreatedEventToServer()
                    onBackClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Create Event",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }

    // Date Picker Modal
    if (showDateRangePicker) {
        DateRangePickerModal(
            onDateRangeSelected = { pair ->
                viewModel.onEventDateChanged(pair.first)
                viewModel.onEventEndDateChanged(pair.second)
            },
            onDismiss = viewModel::onShowDateRangePickerChanged
        )
    }

    // Time Picker Modals
    if (showTimePicker) {
        TimePickerDialog(
            onConfirm = { timePickerState ->
                viewModel.onEventTimeChanged(timePickerState)
                viewModel.onShowTimePickerChanged()
            },
            onDismiss = { viewModel.onShowTimePickerChanged(false) },
            initialHour = eventTime.hour,
            initialMinute = eventTime.minute
        )
    }
    if (showEndTimePicker) {
        TimePickerDialog(
            onConfirm = { timePickerState ->
                viewModel.onEventEndTimeChanged(timePickerState)
                viewModel.onShowEndTimePickerChanged()
            },
            onDismiss = { viewModel.onShowEndTimePickerChanged(false) },
            initialHour = eventTime.hour,
            initialMinute = eventTime.minute
        )
    }
}

@Composable
private fun TimePickerDialog(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
    initialHour: Int,
    initialMinute: Int
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.padding(24.dp),
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 6.dp
            ) {
                DialTimerPicker(
                    onConfirm = onConfirm,
                    onDismiss = onDismiss,
                    initialHour = initialHour,
                    initialMinute = initialMinute
                )
            }
        }
    }
}


