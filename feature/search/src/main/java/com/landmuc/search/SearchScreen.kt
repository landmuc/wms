package com.landmuc.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.landmuc.search.component.SearchEventListLazyColumn
import com.landmuc.search.component.SearchTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchFilteredEventList by viewModel.searchFilteredEventList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllEvents()
    }

    Scaffold(
        topBar = {
            SearchTopAppBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { newQuery ->
                    viewModel.onSearchQueryChanged(newQuery)
                    viewModel.getSearchFilteredEvents(newQuery)
                } ,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchEventListLazyColumn(
                eventList = searchFilteredEventList,
                onEventClick = {})

        }
    }

}