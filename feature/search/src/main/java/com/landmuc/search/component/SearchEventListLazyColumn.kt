package com.landmuc.search.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.landmuc.domain.model.Event

@Composable
fun SearchEventListLazyColumn(
    eventList: List<Event>,
    onEventClick: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 20.dp, bottom = 20.dp)
    ) {
        items(items = eventList) {event ->
            SearchEventCard(
                event = event,
                onEventClick = { onEventClick(event) }
            )
        }
    }
}