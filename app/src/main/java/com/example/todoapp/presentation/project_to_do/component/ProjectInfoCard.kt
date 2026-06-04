package com.example.todoapp.presentation.project_to_do.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.data.dtos.CreateProjectDto

@Composable
fun ProjectInfoCard(
    project : CreateProjectDto
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimension.SMALL_PADDING2,
                horizontal = Dimension.MEDIUM_PADDING1
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8)
            )
            .clip(
                shape = RoundedCornerShape(8)
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
                vertical = Dimension.MEDIUM_PADDING1,
                horizontal = Dimension.MEDIUM_PADDING2
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
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
                    text = project.projectName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 22.sp
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                HorizontalDivider(
                    color = colorResource(
                        id = R.color.text_title
                    ),
                    thickness = 1.5.dp
                )
            }

            Spacer(
                modifier = Modifier
                    .height(
                        Dimension.MEDIUM_PADDING1
                    )
            )

            Text(
                text = project.projectDescription,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 17.sp
                ),
                color = colorResource(
                    id = R.color.text_title
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}