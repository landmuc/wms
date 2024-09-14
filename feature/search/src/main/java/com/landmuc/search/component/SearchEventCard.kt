package com.landmuc.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.landmuc.domain.event.EventStatus
import com.landmuc.domain.model.Event
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.UUID

@Composable
fun SearchEventCard(
    event: Event,
    onEventClick: (Event) -> Unit,
    updateFollowedEventList: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onEventClick(event) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = when(event.eventStatus) {
                is EventStatus.Upcoming -> Color(0xFFFFF9C4)
                is EventStatus.Ongoing -> Color(0xFFE3F2FD)
                is EventStatus.Over -> Color(0xFFF0766D)
            },
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = if (event.isFollowed) Icons.Default.Check else Icons.Default.AddCircle,
                    contentDescription = "Followed icon",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            updateFollowedEventList(event)
                        }
                )

            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Organizer: Lars U.", // TODO: Change to actual name of creator
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = event.eventDate.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }
                SuggestionChip(
                    onClick = { }, // required to define an onClick()
                    label = {
                        Text(
                            text = when(event.eventStatus) {
                                is EventStatus.Upcoming -> EventStatus.Upcoming.text
                                is EventStatus.Ongoing -> EventStatus.Ongoing.text
                                is EventStatus.Over -> EventStatus.Over.text
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = when(event.eventStatus) {
                            is EventStatus.Upcoming -> Color(0xFFFFD54F)
                            is EventStatus.Ongoing -> Color(0xFF64B5F6)
                            is EventStatus.Over -> Color(0xFFF44336)
                        },
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(enabled = false)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewEventListCard() {
    SearchEventCard(
        onEventClick = {},
        updateFollowedEventList = {},
        event = Event(
            id = UUID.randomUUID(),
            title = "Preview Event",
            description = "Description of Preview Event",
            dateCreated = LocalDate(2024,7,11),
            timeCreated = LocalTime(23, 12, 32),
            eventDate = LocalDate(2024,11,11),
            eventTime = LocalTime(7, 14, 21),
            eventEndDate = LocalDate(2024,11,11),
            eventEndTime = LocalTime(7, 14, 21),
            eventStatus = EventStatus.Over,
            isFollowed = true
        )
    )
}

