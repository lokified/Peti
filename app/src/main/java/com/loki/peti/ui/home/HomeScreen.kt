package com.loki.peti.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.peti.ui.common.TopBar
import com.loki.peti.R
import com.loki.peti.ui.navigation.HomeScreens
import com.loki.peti.ui.theme.Teal_100


@Composable
fun HomeScreen(
    openScreen: (String) -> Unit
) {

    TopBar(title = "Home") {

        LazyColumn {

            items(categoryItems) { item ->

                CategoryItem(
                    category = item,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    openScreen(item.route)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryItem,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Teal_100)
            .shadow(elevation = 0.dp, clip = true)
            .clip(RoundedCornerShape(5.dp))
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp)
        ) {

            Image(
                painter = painterResource(id = category.image),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.weight(0.5f))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 30.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = category.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                category.categoryItem.forEach { item ->

                    Text(text = item, fontSize = 14.sp, color = Color.Green)
                }
            }
        }
    }
}

data class CategoryItem(
    @DrawableRes
    val image: Int,
    val title: String,
    val route: String,
    val categoryItem: List<String>
)

val categoryItems = listOf(
    CategoryItem(
        image = R.drawable.food,
        title = "Food",
        route = HomeScreens.FoodScreen.route,
        categoryItem = listOf("2 Bowls of fish")
    ),
    CategoryItem(
        image = R.drawable.expense,
        title = "Expense",
        route = HomeScreens.ExpenseScreen.route,
        categoryItem = listOf("Food")
    ),
    CategoryItem(
        image = R.drawable.sanitation,
        title = "Hygiene and Care",
        route = HomeScreens.HygieneScreen.route,
        categoryItem = emptyList()
    ),
    CategoryItem(
        image = R.drawable.exercise,
        title = "Activities",
        route = HomeScreens.ActivityScreen.route,
        categoryItem = listOf("Training", "Exercise")
    ),
    CategoryItem(
        image = R.drawable.vaccine,
        title = "Vaccine",
        route = HomeScreens.VaccineScreen.route,
        categoryItem = listOf("Rabies Vaccine")
    )
)