package app.allever.android.compose.flow.money.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.allever.android.compose.flow.money.MainViewModel
import app.allever.android.compose.flow.money.R
import app.allever.android.compose.flow.money.theme.GreenDeep
import app.allever.android.compose.flow.money.theme.GreenLight
import app.allever.android.compose.flow.money.theme.GreenMiddle

@Composable
fun FlowListScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            //preview
            FlowListScreenPreview()
            //Sort
            FlowListScreenSort(viewModel)
            //List
            FlowListScreenList()
        }
    }
}

@Composable
fun FlowListScreenSort(viewModel: MainViewModel) {
    LazyRow {
        itemsIndexed(viewModel.sortListState) { index, item ->
            val selected = index == viewModel.sortState.intValue
            val bgColor = if (selected) GreenDeep else Color(0xFFE3F1E9)
            val textColor = if (selected) Color.White else Color.Black
            val iconTint = if (selected) Color.White else Color.Black
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .clip(RoundedCornerShape(90.dp))
                    .clickable {
                        viewModel.sortState.intValue = index
                    }
                    .background(bgColor)
                    .padding(horizontal = 16.dp, vertical = 6.dp)

            ) {
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(iconTint),
                    modifier = Modifier
                        .size(16.dp)
                )
                Text(
                    text = item.name,
                    color = textColor,
                    fontSize = 12.sp,
                    lineHeight = 1.sp,
                    fontWeight = FontWeight.Bold,
                )

            }


        }
    }
}

@Preview
@Composable
fun FlowListScreenList() {
}


@Preview
@Composable
fun FlowListScreenPreview() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = GreenDeep
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(top = 5.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart
            ) {
                //日期
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(45.dp))
                        .background(Color(0xff288A6C))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "2023-04-05",
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp)
                    )
                    //arrow icon
                    Image(
                        painter = painterResource(id = R.drawable.fm_ic_arrow_down),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(12.dp)
                    )
                }

                //预算健康
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(45.dp))
                        .background(
                            GreenMiddle
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(GreenDeep)
                    ) {

                    }

                    Text(
                        text = "预算健康",
                        color = GreenDeep,
                        fontSize = 11.sp,
                        lineHeight = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            //label本月结余
            Text(
                text = "本月结余(元)",
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            //本月结余
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "¥",
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = "8,880.00",
                    color = Color.White,
                    fontSize = 28.sp,
                    lineHeight = 1.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            //支出&收入
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1E916E))
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.fm_ic_arrow_down_2),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(12.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "支出(元)",
                            color = Color.White,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Text(
                        text = "¥8,880.00",
                        color = Color.White,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0x20ffffff))
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.fm_ic_arrow_up_2),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(12.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = "收入(元)",
                            color = Color.White,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Text(
                        text = "¥8,880.00",
                        color = GreenMiddle,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            //进度
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                //月度预算使用进度
                Text(
                    text = "月度预算使用进度",
                    color = Color.White,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                //已用 45% 居右
                Text(
                    text = "已用 45% - 剩余 ¥3,000",
                    color = Color.White,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            //progress
            LinearProgressIndicator(
                progress = { 0.45f },
                trackColor = Color(0xFF1E916E),
                color = GreenLight,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(90.dp))
            )

        }
    }
}

