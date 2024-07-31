package com.landmuc.event_user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.landmuc.domain.model.Event
import com.landmuc.event_user.component.EventUserLazyColumn
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun EventUserScreen(
    eventIdAsString: String,
    viewModel: EventUserViewModel = koinViewModel()
) {
    val stepList by viewModel.stepList.collectAsState()
    val eventIdAsUUID = UUID.fromString(eventIdAsString)

//    LaunchedEffect(Unit) {
//        viewModel.getStepList(eventIdAsUUID)
//    }
    
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "EventTitle - EventUserScreen")
        Text(text = "evenId UUID = $eventIdAsUUID")
        Button(onClick = { viewModel.getStepList(eventIdAsUUID) }) {
            Text(text = "Get StepList")
        }
        EventUserLazyColumn(
            stepList = stepList
        )
    }
}