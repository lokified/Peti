package com.loki.peti.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.peti.domain.models.HomeDetail
import com.loki.peti.ui.theme.Teal_100

@Composable
fun ItemDetail(
    homeDetail: HomeDetail,
    modifier: Modifier = Modifier
) {
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Teal_100)
            .shadow(elevation = 0.dp, shape = RoundedCornerShape(5.dp))
    ) {
        
        Row(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(text = homeDetail.titleDescription, fontSize = 18.sp)

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                if (homeDetail.expenseAmount != null) {
                    Text(text = "$ ${homeDetail.expenseAmount}", fontSize = 16.sp)
                }

                if (homeDetail.timeRecord != null && homeDetail.dateRecord == null) {
                    Text(text = "${homeDetail.timeRecord}", fontSize = 16.sp)
                }

                if (homeDetail.dateRecord != null) {
                    Text(text = "${homeDetail.timeRecord}", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${homeDetail.dateRecord}", fontSize = 16.sp)
                }

                if (homeDetail.startTime != null) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${homeDetail.startTime}", fontSize = 14.sp, modifier = Modifier.padding(end = 4.dp))
                        Text(text = "-")
                        Text(text = "${homeDetail.endTime}", fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }
}