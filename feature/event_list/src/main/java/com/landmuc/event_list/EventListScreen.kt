package com.landmuc.event_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.landmuc.domain.model.Event
import com.landmuc.event_list.component.EventListLazyColumn
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(
    onEventClick: (Event) -> Unit,
    viewModel: EventListViewModel = koinViewModel()
) {
    val eventList by viewModel.eventList.collectAsState()

    //TODO: Try viewmodel init block?
    LaunchedEffect(Unit) {
        viewModel.getFollowedEvents()
//        viewModel.getEventList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Followed Events",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    ) }
                )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            EventListLazyColumn(
                eventList = eventList,
                onEventClick = { event -> onEventClick(event) }
            )
        }
    }


}