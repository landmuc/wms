package com.landmuc.create_event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.landmuc.create_event.component.DateRangePickerModal
import com.landmuc.create_event.component.DialTimerPicker
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
                title = { Text(text = "Add new Event")},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Event Title",
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = eventTitle,
                onValueChange = viewModel::onEventTitleChanged,
                label = { Text(text = "Event title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Date",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "$eventDate - $eventEndDate",
                    onValueChange = {},
                    readOnly = true,
                )
                Spacer(modifier = Modifier.weight(0.1f))
                IconButton(
                    onClick = { viewModel.onShowDateRangePickerChanged() },
                ) {
                    Image(painter = painterResource(id = R.drawable.date_range), contentDescription = "Date Range" )
                }
                Spacer(modifier = Modifier.weight(0.1f))
                if (showDateRangePicker) DateRangePickerModal(
                    onDateRangeSelected = { pair ->
                        viewModel.onEventDateChanged(pair.first)
                        viewModel.onEventEndDateChanged(pair.second)
                    },
                    onDismiss = viewModel::onShowDateRangePickerChanged
                )
            }
            Text(
                text = "Time",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "${eventTime.hour}:${eventTime.minute}",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.width(75.dp)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                IconButton(
                    onClick = {
                        viewModel.onShowTimePickerChanged()
                        viewModel.onShowEndTimePickerChanged(false)
                              },
                ) {
                    Image(painter = painterResource(id = R.drawable.baseline_access_time_24), contentDescription = "Time" )
                }
                Spacer(modifier = Modifier.weight(0.20f))
                Text(
                    text = "to"
                )
                Spacer(modifier = Modifier.weight(0.20f))
                OutlinedTextField(
                    value = "${eventEndTime.hour}:${eventEndTime.minute}",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.width(75.dp)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                IconButton(
                    onClick = {
                        viewModel.onShowEndTimePickerChanged()
                        viewModel.onShowTimePickerChanged(false)
                              },
                ) {
                    Image(painter = painterResource(id = R.drawable.baseline_access_time_filled_24), contentDescription = "Time" )
                }
            }

            if (showTimePicker) DialTimerPicker(
                onConfirm = { timePickerState ->
                    viewModel.onEventTimeChanged(timePickerState)
                    viewModel.onShowTimePickerChanged()
                }
            )
            if (showEndTimePicker) DialTimerPicker(
                onConfirm = { timePickerState ->
                    viewModel.onEventEndTimeChanged(timePickerState)
                    viewModel.onShowEndTimePickerChanged()
                }
            )
            Text(
                text = "Description",
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = eventDescription,
                onValueChange = viewModel::onEventDescriptionChanged,
                label = { Text(text = "Event Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    viewModel.sendCreatedEventToServer()
                    onBackClick()
                }) {
                    Text(text = "Create Event")
                }
            }
        }
    }
}


