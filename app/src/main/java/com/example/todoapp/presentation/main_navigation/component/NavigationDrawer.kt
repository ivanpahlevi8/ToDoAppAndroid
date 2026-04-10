package com.example.todoapp.presentation.main_navigation.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.todoapp.R
import com.example.todoapp.core.value.Constants

@Composable
fun NavigationDrawer(
    item: @Composable () -> Unit,
    itemList: List<NavBarItem>,
    selectedItem: Int,
    onClick: (Int) -> Unit,
    drawerState: DrawerState,
    onProfile : () -> Unit,
    onLogout : () -> Unit,
) {
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences(Constants.AUTH_PREFERENCES, Context.MODE_PRIVATE)
    }

    // Wrap these in remember so they don't cause infinite re-reads during composition
    val userName = remember { sharedPrefs.getString(Constants.USER_NAME, "No Name") ?: "No Name" }
    val email = remember { sharedPrefs.getString(Constants.EMAIL, "No Email") ?: "No Email" }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Wrap in a Column to ensure background color is applied from the Sheet
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.person_icon
                        ),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(androidx.compose.foundation.shape.CircleShape), // Make it circular
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${userName} | ${email}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W600,
                            fontSize = 18.sp,
                        ),
                        color = colorResource(
                            id = R.color.text_title,
                        )
                    )

                    TextButton(
                        onClick = {
                            onProfile()
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "View Profile",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Top
                    ) {
                        itemList.forEachIndexed { index, navItem ->
                            NavigationDrawerItem(
                                label = {
                                    Text(
                                        text = navItem.title,
                                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                selected = selectedItem == index,
                                onClick = { onClick(index) },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = navItem.icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Logout",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            onLogout()
                        },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.logout_ic
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        selected = false
                    )
                }
            }
        },
        gesturesEnabled = true,
    ) {
        // IMPORTANT: Ensure your main content has a background
        androidx.compose.material3.Surface(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            item()
        }
    }
}