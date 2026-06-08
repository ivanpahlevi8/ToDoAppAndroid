package com.example.todoapp.core.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun CustomAlertDialog(
    title : String,
    content : String,
    onCancel : () -> Unit,
    onApprove : () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = colorResource(
                id = R.color.bad_start
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = Dimension.SMALL_PADDING2,
                        horizontal = Dimension.MEDIUM_PADDING1
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800,
                        fontSize = 22.sp,
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING1
                        )
                )

                Text(
                    text = content,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                    ),
                    color = colorResource(
                        id = R.color.text_title
                    ),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(
                            Dimension.MEDIUM_PADDING2
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // cancel button
                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                onCancel()
                            }
                            .padding(
                                Dimension.SMALL_PADDING1
                            )
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.error_color
                                )
                            )
                            .padding(
                                vertical = Dimension.SMALL_PADDING2,
                                horizontal = Dimension.MEDIUM_PADDING1
                            )
                    ) {
                        Text(
                            text = "CANCEL",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp
                            ),
                            color = colorResource(
                                id = R.color.white
                            )
                        )
                    }
                    
                    Spacer(
                        modifier = Modifier
                            .width(
                                Dimension.SMALL_PADDING2
                            )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                onApprove()
                            }
                            .padding(
                                Dimension.SMALL_PADDING1
                            )
                            .clip(
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = colorResource(
                                    id = R.color.excellent_start
                                )
                            )
                            .padding(
                                vertical = Dimension.SMALL_PADDING2,
                                horizontal = Dimension.MEDIUM_PADDING1
                            )
                    ) {
                        Text(
                            text = "CONFIRM",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp
                            ),
                            color = colorResource(
                                id = R.color.white
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AlertDialogPreview(){
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
            CustomAlertDialog(
                title = "Warning!!!",
                content = "This is warning dialog content, please be aware of something",
                onCancel = {},
                onApprove = {}
            )
        }
    }
}