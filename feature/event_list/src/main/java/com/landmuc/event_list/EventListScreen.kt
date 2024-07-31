package com.landmuc.event_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.landmuc.domain.model.Event
import com.landmuc.event_list.component.EventListLazyColumn
import org.koin.androidx.compose.koinViewModel

@Composable
fun EventListScreen(
    onEventClick: (Event) -> Unit,
    viewModel: EventListViewModel = koinViewModel()
) {
    val eventList by viewModel.eventList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getEventList()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        EventListLazyColumn(
            eventList = eventList,
            onEventClick = { event ->  onEventClick(event) }
        )
    }
}