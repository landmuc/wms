package com.landmuc.event_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.landmuc.domain.model.Event
import com.landmuc.event_list.component.EventListLazyColumn
import com.landmuc.event_list.di.eventListViewModelModule
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(
    onEventClick: (Event) -> Unit,
    navigateToSearchScreen: () -> Unit,
    viewModel: EventListViewModel = koinViewModel()
) {
    val eventList by viewModel.eventList.collectAsState()

    //TODO: Try viewmodel init block?
    LaunchedEffect(Unit) {
        viewModel.getFollowedEvents()
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
        },
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom
            ){
                ExtendedFloatingActionButton(
                    onClick = { navigateToSearchScreen() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.feature_event_list_description_search)
                    )
                }
                FloatingActionButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.feature_event_list_description_search),
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
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



@Preview
@Composable
fun PreviewEventListScreen() {
    KoinApplication(
        application = { modules(eventListViewModelModule) }
    ) {
        EventListScreen(
            onEventClick = { },
            navigateToSearchScreen = { }
        )
    }
}