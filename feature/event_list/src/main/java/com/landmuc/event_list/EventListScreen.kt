package com.landmuc.event_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.landmuc.event_list.component.EventListLazyColumn
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventListScreen(
    viewModel: EventListViewModel = koinViewModel()
) {
    val eventList by viewModel.eventList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getEventList()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EventListLazyColumn(
            eventList = eventList
        )
        Button(onClick = viewModel::getEventList) {
            Text(text = "Get EventList")
        }
    }
}