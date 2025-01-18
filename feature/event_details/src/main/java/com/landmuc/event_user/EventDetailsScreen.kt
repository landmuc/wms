package com.landmuc.event_user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.landmuc.domain.event.EventStatus
import com.landmuc.event_user.component.EventTimeInfo
import com.landmuc.event_user.component.StepLazyColumn
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    onBackClick: () -> Unit,
    eventTitle: String,
    eventIdAsString: String,
    viewModel: EventDetailsViewModel = koinViewModel()
) {
    val stepList by viewModel.stepList.collectAsState()
    val event by viewModel.event.collectAsState()
    val eventIdAsUUID = UUID.fromString(eventIdAsString)

    LaunchedEffect(Unit) {
        viewModel.getStepList(eventIdAsUUID)
        viewModel.getEvent(eventIdAsUUID)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(eventTitle) },
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
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when(event?.eventStatus) {
                        is EventStatus.Upcoming -> Color(0xFFFFF9C4)
                        is EventStatus.Ongoing -> Color(0xFFE3F2FD)
                        is EventStatus.Over -> Color(0xFFF0766D)
                        null -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    event?.eventStatus?.let { status ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            SuggestionChip(
                                onClick = { },
                                label = {
                                    Text(
                                        text = status.text,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White
                                    )
                                },
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = when(status) {
                                        is EventStatus.Upcoming -> Color(0xFFFFD54F)
                                        is EventStatus.Ongoing -> Color(0xFF64B5F6)
                                        is EventStatus.Over -> Color(0xFFF44336)
                                    }
                                )
                            )
                        }
                    }

                    Text(
                        text = event?.description ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    event?.let { currentEvent ->
                        EventTimeInfo(
                            startDate = currentEvent.eventDate,
                            startTime = currentEvent.eventTime,
                            endDate = currentEvent.eventEndDate,
                            endTime = currentEvent.eventEndTime
                        )
                    }
                }
            }

            Text(
                text = "Steps",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            
            StepLazyColumn(
                stepList = stepList,
                modifier = Modifier.weight(1f)
            )
        }
    }
}