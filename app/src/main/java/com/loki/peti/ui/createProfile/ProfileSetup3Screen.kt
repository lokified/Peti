package com.loki.peti.ui.createProfile

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loki.peti.ui.common.ButtonSection
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.navigation.OnBoardingScreens
import com.loki.peti.ui.theme.Green_Light
import com.loki.peti.ui.theme.Teal_100
import com.loki.peti.ui.theme.Teal_500
import java.util.*

@Composable
fun ProfileSetup3Screen(
    viewModel: ProfileSetUpViewModel = hiltViewModel(),
    openScreen: (String) -> Unit
) {

    val context = LocalContext.current

    if (viewModel.feedTime.value.isNotEmpty()) {
        viewModel.addFeedTime(viewModel.feedTime.value)
    }

    TopBar(
        title = "Profile Setup",
        bottomBar = {
            ButtonSection(
                text = "Next",
                modifier = Modifier.padding(16.dp)
            ) {
                openScreen(OnBoardingScreens.ProfileSetup4Screen.route)
            }
        }
    ) {

        Column(modifier = Modifier) {
            TopSection(
                Modifier.padding(top = 50.dp, start = 24.dp, end = 24.dp, bottom = 100.dp)
            )

            FeedingTimeSection(
                modifier = Modifier.padding(16.dp),
                context = context,
                viewModel = viewModel
            )

            FeedingTimeList(
                feedingList = viewModel.feedingList,
                onDeleteClick = { viewModel.deleteFeedTime(it) },
                modifier = Modifier.padding(bottom = 66.dp)
            )
        }
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {

    var value by remember { mutableStateOf(0) }
    
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        
        Text(
            text = "How many times do you need to feed your pet?",
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                ActionButton(
                    color = Green_Light,
                    icon = Icons.Rounded.Remove
                ) {
                    value--
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = value.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(12.dp))

                ActionButton(
                    color = MaterialTheme.colors.error,
                    icon = Icons.Default.Add
                ) {
                    value++
                }
            }
        }
    }
}

@Composable
fun FeedingTimeSection(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: ProfileSetUpViewModel
) {

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val timePicker = TimePickerDialog(
        context,
        {_, hour : Int, minute: Int ->
            viewModel.feedTime.value = "$hour:$minute"
        }, mHour, mMinute, false
    )


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Feeding Time Reminders",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        ActionButton(
            color = MaterialTheme.colors.primary,
            icon = Icons.Default.Add
        ) {
            timePicker.show()
        }
    }
}

@Composable
fun FeedingTimeList(
    feedingList: List<String>,
    onDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {

        items(feedingList) { feed ->

            FeedingItem(
                time = feed,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                onDeleteClick = { onDeleteClick(feed) }
            )
        }
    }

}

@Composable
fun FeedingItem(
    modifier: Modifier = Modifier,
    time: String,
    onDeleteClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Teal_100, shape = RoundedCornerShape(3.dp))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = null,
                tint = Teal_500
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = time, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Gray)

            Spacer(modifier = Modifier.weight(1f))

            ActionButton(color = Green_Light, icon = Icons.Default.DeleteOutline) {
                onDeleteClick()
            }
        }


    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    color: Color,
    icon: ImageVector,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .size(35.dp)
            .background(color = color, shape = RoundedCornerShape(30.dp))
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
