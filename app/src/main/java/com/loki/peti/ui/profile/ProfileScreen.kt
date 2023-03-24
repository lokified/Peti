package com.loki.peti.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loki.peti.R
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.navigation.OnBoardingScreens
import com.loki.peti.ui.theme.Teal_100

@Composable
fun ProfileScreen(
    clearAndOpen: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    TopBar(title = "Profile Settings") {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp)
        ) {

            TopSelectionSection(
                viewModel = viewModel,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            )

            ProfileImageSection(
                viewModel = viewModel
            )

            ProfileSection(viewModel = viewModel)

            ChangeFeedSection(viewModel = viewModel, modifier = Modifier.padding(vertical = 4.dp))

            NewProfileSection(modifier = Modifier.padding(vertical = 4.dp)) {
                clearAndOpen(OnBoardingScreens.ProfileSetup1Screen.route)
            }

            LogoutSection(modifier = Modifier.padding(top = 4.dp)) {
                clearAndOpen(OnBoardingScreens.LoginScreen.route)
            }
        }
    }
}

@Composable
fun TopSelectionSection(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {

    Row(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Change Pet", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                IconButton(
                    onClick = { /*TODO*/ }
                ) {

                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            
            Box(
                modifier = Modifier
                    .padding(vertical = 1.dp, horizontal = 8.dp)
                    .shadow(elevation = 0.dp, shape = RoundedCornerShape(2.dp)),
                contentAlignment = Alignment.Center
            ) {

                Text(text = viewModel.petProfiles[0], color = Color.Gray)
            }
        }
    }
}

@Composable
fun ProfileImageSection(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(height = 160.dp, width = 160.dp)
                .clip(CircleShape)
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.Transparent
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }

}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {

    val profile = viewModel.petProfileDetail.value

    Column(modifier = modifier) {
        
        ProfileRow(
            rowTitle = "Pet Name",
            rowContent = profile.name,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {

        }

        ProfileRow(
            rowTitle = "Birthday",
            rowContent = profile.birthday,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {

        }

        ProfileRow(
            rowTitle = "Gender",
            rowContent = profile.gender,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {

        }
    }

}


@Composable
fun ChangeFeedSection(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {

    Column(modifier = modifier) {
        
        Text(
            text = "Change Feeding Time Reminders",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        
        Box(modifier = Modifier.background(Teal_100)) {
            
            Column(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            ) {

                viewModel.petProfileDetail.value.feedingTime.forEach { feed ->
                    FeedRow(rowTitle = feed) {

                    }
                }
            }
        }
    }
}

@Composable
fun NewProfileSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(Teal_100)
            .clickable { onClick() }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            Text(text = "Create a new Profile", fontSize = 18.sp, fontWeight = FontWeight.Normal)

            Spacer(modifier = Modifier.weight(1f))

            Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun LogoutSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(Teal_100)
            .clickable { onClick() }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        ) {

            Text(text = "Logout", fontSize = 18.sp, fontWeight = FontWeight.Normal)

            Spacer(modifier = Modifier.weight(1f))

            Icon(imageVector = Icons.Rounded.Logout, contentDescription = null)
        }
    }
}

@Composable
fun FeedRow(
    modifier: Modifier = Modifier,
    rowTitle: String,
    onRowClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 24.dp)
            .clickable { onRowClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(text = rowTitle, color = Color.DarkGray, fontSize = 16.sp)

        Spacer(modifier = Modifier.weight(1f))

        Icon(imageVector = Icons.Outlined.Timer, contentDescription = null)
    }
}


@Composable
fun ProfileRow(
    modifier: Modifier = Modifier,
    rowTitle: String,
    rowContent: String,
    onIconClick: () -> Unit
) {

    Box(
        modifier = modifier.background(Teal_100)
    ) {
        
        Row(modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)) {

            Column {
                Text(text = rowTitle, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = rowContent, color = Color.DarkGray, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    onIconClick()
                }
            ) {
                
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null
                )
            }
        }
    }
}