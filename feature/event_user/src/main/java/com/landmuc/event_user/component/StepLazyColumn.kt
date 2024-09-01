package com.landmuc.event_user.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.landmuc.domain.model.Step

@Composable
fun StepLazyColumn(
    stepList: List<Step>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 20.dp, bottom = 20.dp)
    ) {
        items(items = stepList) { step ->
            StepCard(
                step = step,
                onStepClick = { /*TODO*/ }
            )
        }
    }
}