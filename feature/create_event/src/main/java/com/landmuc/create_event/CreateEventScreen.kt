package com.landmuc.create_event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
//                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = eventTitle,
                onValueChange = viewModel::onEventTitleChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Event title") }
            )

            OutlinedTextField(
                value = eventDescription,
                onValueChange = viewModel::onEventDescriptionChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Event Description") }
            )

            Button(onClick = viewModel::onShowDateRangePickerChanged) {
                Text(text = "Pick Date Range")
                if (showDateRangePicker) DateRangePickerModal(
                    onDateRangeSelected = { pair ->
                        viewModel.onEventDateChanged(pair.first)
                        viewModel.onEventEndDateChanged(pair.second)
                    },
                    onDismiss = viewModel::onShowDateRangePickerChanged
                )
            }

            Text(text = "Event Date: $eventDate")
            Text(text = "Event End Date: $eventEndDate")

            Button(onClick = viewModel::onShowTimePickerChanged) {
                Text(text = "Select Time")
            }
            if (showTimePicker) DialTimerPicker(
                onConfirm = { timePickerState ->
                    viewModel.onEventTimeChanged(timePickerState)
                },
                onDismiss = viewModel::onShowTimePickerChanged
            )



            Text(text = "Event Time: $eventTime")
        }
    }
}


