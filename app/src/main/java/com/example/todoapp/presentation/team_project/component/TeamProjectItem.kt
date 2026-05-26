package com.example.todoapp.presentation.team_project.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Constants
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateProjectDto
import com.example.todoapp.domain.models.UserModel
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TeamProjectItem(
    projectItem : CreateProjectDto,
    userLead : UserModel,
    onProjectDetail : (Int) -> Unit,
) {
    var showProjectDesc by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(
                Dimension.MEDIUM_PADDING1
            )
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium
            )
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(
                            id = R.color.time_line_card_color2
                        ),
                        colorResource(
                            id = R.color.time_line_card_color3
                        )
                    ),
                    start = Offset(0F, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                )
            )
            .padding(
                vertical = Dimension.MEDIUM_PADDING1,
                horizontal = Dimension.MEDIUM_PADDING2
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier
                        .width(
                            IntrinsicSize.Max
                        )
                ) {
                    Text(
                        text = projectItem.projectName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 20.sp
                        ),
                        color = colorResource(
                            id = R.color.text_title
                        )
                    )

                    HorizontalDivider(
                        thickness = 1.5.dp,
                        color = colorResource(
                            id = R.color.black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.SMALL_PADDING2
                        )
                )

                Row(
                    modifier = Modifier
                        .padding(
                            start = 2.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Project Description",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
                        )
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
                            .clickable {

                            }
                            .padding(
                                Dimension.SMALL_PADDING1
                            )
                            .clip(
                                shape = CircleShape
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.time_line_card_color4
                                )
                            )
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.keyboard_arrow_down_ic
                            ),
                            contentDescription = "Arrow Indicator",
                            modifier = Modifier
                                .size(18.dp),
                            tint = colorResource(
                                id = R.color.black
                            )
                        )
                    }
                }

                AnimatedVisibility(
                    visible = showProjectDesc
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .padding(
                                start = Dimension.MEDIUM_PADDING1,
                                end = Dimension.SMALL_PADDING2
                            )
                            .fillMaxWidth()
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.time_line_card_color1
                                )
                            )
                            .padding(
                                vertical = Dimension.SMALL_PADDING2,
                                horizontal = Dimension.MEDIUM_PADDING1
                            )
                    ){
                        Text(
                            text = projectItem.projectDescription,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp
                            ),
                            color = colorResource(
                                id = R.color.text_title,
                            ),
                            textAlign = TextAlign.Justify,
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.person_ic
                        ),
                        contentDescription = "Person Icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(Dimension.SMALL_PADDING2)
                    )

                    Text(
                        text = "Lead By",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clip(
                                shape = RoundedCornerShape(6.dp)
                            )
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(
                                            id = R.color.average_start
                                        ),
                                        colorResource(
                                            id = R.color.average_end
                                        )
                                    ),
                                    start = Offset(0F, Float.POSITIVE_INFINITY),
                                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                                )
                            )
                            .padding(
                                horizontal = Dimension.SMALL_PADDING2
                            )
                    ) {
                        Text(
                            text = userLead.userName,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp
                            ),
                            color = colorResource(
                                id = R.color.black
                            )
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.calendar_month_ic
                        ),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Text(
                        text = "Created At",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clip(
                                shape = RoundedCornerShape(6.dp)
                            )
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(
                                            id = R.color.excellent_start
                                        ),
                                        colorResource(
                                            id = R.color.excellent_end
                                        )
                                    ),
                                    start = Offset(0F, Float.POSITIVE_INFINITY),
                                    end = Offset(Float.POSITIVE_INFINITY, 0F)
                                )
                            )
                            .padding(
                                horizontal = Dimension.SMALL_PADDING2
                            )
                    ) {
                        Text(
                            text = (projectItem.projectCreatedAt ?: "").split("T")[0],
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp
                            ),
                            color = colorResource(
                                id = R.color.black
                            )
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.check_circle_ic
                        ),
                        contentDescription = "Check circle icon",
                        modifier = Modifier
                            .size(20.dp),
                        tint = colorResource(
                            id = R.color.text_title
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Text(
                        text = "Project Status",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    if(projectItem.projectStatus == Constants.PROJECT_STATUS_CREATED){
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 3.dp,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clip(
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            colorResource(
                                                id = R.color.time_line_card_color1
                                            ),
                                            colorResource(
                                                id = R.color.time_line_card_color2
                                            )
                                        ),
                                        start = Offset(0F, Float.POSITIVE_INFINITY),
                                        end = Offset(Float.POSITIVE_INFINITY, 0F)
                                    )
                                )
                                .padding(
                                    horizontal = Dimension.SMALL_PADDING2
                                )
                        ) {
                            Text(
                                text = Constants.PROJECT_STATUS_CREATED,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp
                                ),
                                color = colorResource(
                                    id = R.color.black
                                )
                            )
                        }
                    } else if(projectItem.projectStatus == Constants.PROJECT_STATUS_ON_GOING){
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 3.dp,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clip(
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            colorResource(
                                                id = R.color.time_line_card_color2
                                            ),
                                            colorResource(
                                                id = R.color.time_line_card_color3
                                            )
                                        ),
                                        start = Offset(0F, Float.POSITIVE_INFINITY),
                                        end = Offset(Float.POSITIVE_INFINITY, 0F)
                                    )
                                )
                                .padding(
                                    horizontal = Dimension.SMALL_PADDING2
                                )
                        ) {
                            Text(
                                text = Constants.PROJECT_STATUS_ON_GOING,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp
                                ),
                                color = colorResource(
                                    id = R.color.white
                                )
                            )
                        }
                    } else if(projectItem.projectStatus == Constants.PROJECT_STATUS_REVIEWED) {
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 3.dp,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clip(
                                    shape = RoundedCornerShape(6.dp)
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
                                    horizontal = Dimension.SMALL_PADDING2
                                )
                        ) {
                            Text(
                                text = Constants.PROJECT_STATUS_REVIEWED,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp
                                ),
                                color = colorResource(
                                    id = R.color.white
                                )
                            )
                        }
                    } else if (projectItem.projectStatus == Constants.PROJECT_STATUS_FINISHED) {
                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 3.dp,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clip(
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            colorResource(
                                                id = R.color.time_line_card_color4
                                            ),
                                            colorResource(
                                                id = R.color.time_line_card_color5
                                            )
                                        ),
                                        start = Offset(0F, Float.POSITIVE_INFINITY),
                                        end = Offset(Float.POSITIVE_INFINITY, 0F)
                                    )
                                )
                                .padding(
                                    horizontal = Dimension.SMALL_PADDING2
                                )
                        ) {
                            Text(
                                text = Constants.PROJECT_STATUS_FINISHED,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp
                                ),
                                color = colorResource(
                                    id = R.color.black
                                )
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        onProjectDetail(
                            projectItem.projectId ?: 0
                        )
                    }
                    .padding(
                        Dimension.SMALL_PADDING1
                    )
                    .clip(
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        color = colorResource(
                            id = R.color.average_end
                        )
                    )
                    .padding(
                        Dimension.SMALL_PADDING2
                    )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.info_ic
                    ),
                    contentDescription = "Info Icon",
                    modifier = Modifier
                        .size(22.dp),
                    tint = colorResource(
                        id = R.color.white
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TeamProjectItemPreview() {
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
            TeamProjectItem(
                projectItem = CreateProjectDto(
                    projectName = "Test Project Name",
                    projectDescription = "Test lorem ipsum dolor sit amet. lorem ipsum dolor sit amet. lorem ipsum dolor sit amet",
                    projectCreatedAt = "2026-05-23T03:54:38.7365425",
                    projectStatus = Constants.PROJECT_STATUS_FINISHED
                ),
                userLead = UserModel(
                    userId = "",
                    userName = "ivanpahlevi8",
                    userEmail = "",
                    userFirstName = "",
                    userCreatedAt = "",
                    userLastName = "",
                    userPhoneNumber = ""
                ),
                onProjectDetail = {}
            )
        }
    }
}