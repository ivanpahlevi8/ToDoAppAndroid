package com.example.todoapp.presentation.main_navigation.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.core.value.Dimension
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun BottomNavigationBar(
    listItem : List<NavBarItem>,
    selectedItem : Int,
    onClickItem : (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        listItem.forEachIndexed { index, navBarItem ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = {
                    onClickItem(index)
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = navBarItem.icon
                        ),
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = navBarItem.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(
                        id = R.color.body
                    ),
                    unselectedTextColor = colorResource(
                        id = R.color.body
                    ),
                    indicatorColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationBarPreview() {
    ToDoAppTheme {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(
                    Dimension.MEDIUM_PADDING2
                )
        ) {
            BottomNavigationBar(
                listItem =  listOf(
                    NavBarItem(
                        title = "Movie Recommendation",
                        icon = R.drawable.movie_icon,
                    ),
                    NavBarItem(
                        title = "Search Movie",
                        icon = R.drawable.search_movie_icon,
                    ),
                    NavBarItem(
                        title = "Search Shows",
                        icon = R.drawable.search_tv_shows,
                    ),
                    NavBarItem(
                        title = "Soon Theater",
                        icon = R.drawable.soon_on_theater,
                    )
                ),
                selectedItem = 1,
                onClickItem = {}
            )
        }
    }
}

// create item data class
data class NavBarItem(
    val title : String,
    @DrawableRes val icon : Int,
)