package com.example.todoapp.presentation.team_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todoapp.domain.models.teams.TeamModel

@Composable
fun TeamItemList(
    itemList : List<TeamModel>,
    userId : String,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            count = itemList.size
        ) {
            index : Int ->
            TeamItemCard(
                teamModel = itemList[index],
                isTeamLead = itemList[index].teamLeaderId == userId
            )
        }
    }
}