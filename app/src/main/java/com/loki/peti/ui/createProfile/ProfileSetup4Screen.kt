package com.loki.peti.ui.createProfile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.peti.R
import com.loki.peti.domain.models.Pet
import com.loki.peti.ui.common.ButtonSection
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.navigation.OnBoardingScreens
import com.loki.peti.ui.theme.Teal_100

@Composable
fun ProfileSetup4Screen(
    clearAndOpen: (String) -> Unit
) {

    TopBar(
        title = "Profile Summary",
        bottomBar = {
            ButtonSection(text = "Save Profile", modifier = Modifier.padding(16.dp)) {
                clearAndOpen(OnBoardingScreens.HomeScreen.route)
            }
        }
    ) {

        val pet = Pet(
            name = "Loki",
            birthday = "12/2/2022",
            gender = "Male",
            feedingTime = listOf("8:00 AM", "01:00 PM", "5:00 PM", "10:00 PM"),
            image = ""
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(bottom = 66.dp)) {

            ImageSection(modifier = Modifier.padding(16.dp))

            PetDetailSection(pet = pet, modifier = Modifier.padding(horizontal = 16.dp))
        }

    }
}

@Composable
fun ImageSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Pet Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

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

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Edit Photo",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun PetDetailSection(
    modifier: Modifier = Modifier,
    pet: Pet
) {

    Column(modifier = modifier) {

        DetailRow(rowTitle = "Pet Name", rowContent = pet.name)
        DetailRow(rowTitle = "Birthday", rowContent = pet.birthday)
        DetailRow(rowTitle = "Gender", rowContent = pet.gender)

        Text(
            text = "Feeding Time",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        pet.feedingTime.map { time ->

            DetailRow(rowContent = time)
        }
    }
}

@Composable
fun DetailRow(
    modifier: Modifier = Modifier,
    rowTitle: String = "",
    rowContent: String
) {

    Box(
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(color = Teal_100, shape = RoundedCornerShape(5.dp))
            .fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.padding(vertical = 18.dp, horizontal = 8.dp)
        ) {

            Text(text = rowTitle, fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = rowContent, fontWeight = FontWeight.Normal, fontSize = 16.sp)
        }
    }
}