package com.example.todoapp.presentation.team_detail.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.TeamRoleDto
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TeamRoleItem(
    teamRoleDto: TeamRoleDto,
    onDelete : (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(
                horizontal = Dimension.SMALL_PADDING2
            )
            .clip(
                shape = RoundedCornerShape(
                    8.dp
                )
            )
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(
                    8.dp
                )
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(
                            id = R.color.time_line_card_color3
                        ),
                        colorResource(
                            id = R.color.time_line_card_color4
                        )
                    ),
                    start = Offset(0F, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                )
            )
            .padding(
                vertical = Dimension.SMALL_PADDING1,
                horizontal = Dimension.MEDIUM_PADDING1
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = teamRoleDto.roleName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W800,
                    fontSize = 14.sp
                ),
                color = colorResource(
                    id = R.color.white
                )
            )

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.SMALL_PADDING2
                    )
            )

            VerticalDivider(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING2 * 2
                    ),
                color = colorResource(
                    id = R.color.text_title
                ),
                thickness = 1.dp
            )

            Spacer(
                modifier = Modifier
                    .width(
                        Dimension.SMALL_PADDING2
                    )
            )

            Box(
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple()
                    ) {
                        onDelete(
                            teamRoleDto.teamRoleId ?: 0
                        )
                    }
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        color = colorResource(
                            id = R.color.error_color
                        )
                    )
                    .padding(
                        Dimension.SMALL_PADDING2
                    )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.delete_forever_ic
                    ),
                    contentDescription = "Delete Forever Icon",
                    tint = colorResource(
                        id = R.color.white
                    ),
                    modifier = Modifier
                        .size(14.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamRoleItemPreview(){
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.SMALL_PADDING2
                )
        ) {
            TeamRoleItem(
                teamRoleDto = TeamRoleDto(
                    roleName = "Software Engineer",
                    teamId = 1
                ),
                onDelete = {}
            )
        }
    }
}