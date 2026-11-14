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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import app.allever.android.compose.flow.money.data.Type
import app.allever.android.compose.flow.money.theme.GreenDeep
import app.allever.android.compose.flow.money.theme.GreenLight
import app.allever.android.compose.flow.money.theme.GreenMiddle

@Composable
fun FlowListScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                //preview
                FlowListScreenPreview()
            }

            item {
                //Sort
                FlowListScreenSort(viewModel)
            }

            item {
                //List
                FlowListScreenList(viewModel)
            }

            item {
                //weekly report
                FlowListScreenWeeklyReport()
            }


        }
    }
}

@Composable
fun FlowListScreenWeeklyReport() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE4F6ED))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fm_ic_default_icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(GreenDeep),
            modifier = Modifier.size(22.dp)
        )

        Column(modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(1f)) {
            //保持得很好
            //本周非必要支出环比下降4%
            Text(
                text = "保持得很好",
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp)
            )
            Text(
                text = "本周非必要支出环比下降4%",
                color = Color.Black,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(0.dp)
            )

        }

        //圆角背景，居右： 看周报
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(90.dp))
                .background(Color(0xFFD8E8E0))
                .padding(horizontal = 8.dp, vertical = 4.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "看周报",
                color = GreenDeep,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp)
            )
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
                    modifier = Modifier.size(16.dp)
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

@Composable
fun FlowListScreenList(viewModel: MainViewModel) {
    viewModel.dailyRecordList.forEachIndexed { index, item ->
        val topMargin = if (index == 0) 16.dp else 8.dp
        val bottomMargin = if (index == viewModel.dailyRecordList.size - 1) 16.dp else 0.dp
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = topMargin, bottom = bottomMargin)
        ) {
            //time line
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    //date
                    Text(
                        text = item.title,
                        color = Color.Black,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp)
                    )
                    //week
                    Text(
                        text = item.week,
                        color = Color.Black,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                //Row 收入、支出居右
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    //income
                    Text(
                        text = "入 ¥${item.income}",
                        color = GreenDeep,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp)
                    )
                    //outcome
                    Text(
                        text = "支 ¥${item.outcome}",
                        color = Color.Black,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            //detailCard
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                item.list.forEachIndexed { index, detail ->
                    val marginTop = if (index == 0) 16.dp else 8.dp
                    val marginBottom = if (index == item.list.size - 1) 16.dp else 8.dp

                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .padding(
                                start = 16.dp, top = marginTop, bottom = marginBottom, end = 16.dp
                            )
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(GreenMiddle)
                        ) {
                            Image(
                                painter = painterResource(id = detail.icon),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(GreenDeep),
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        ) {
                            //title
                            Text(
                                text = detail.title,
                                color = Color.Black,
                                fontSize = 15.sp,
                                lineHeight = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(0.dp)
                            )

                            Row {
                                //sort
                                Text(
                                    text = detail.sort,
                                    color = Color.Gray,
                                    fontSize = 11.sp,
                                    lineHeight = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .weight(1f)
                                )
                                //time-payType
                                Text(
                                    text = "${detail.time} ${detail.payType}",
                                    color = Color.Gray,
                                    fontSize = 11.sp,
                                    lineHeight = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .weight(2f)
                                )
                            }

                        }

                        //value
                        val valueColor = if (detail.type == Type.INCOME) GreenDeep else Color.Black
                        val pre = if (detail.type == Type.INCOME) "+" else "-"
                        Text(
                            modifier = Modifier.padding(0.dp),
                            text = "${pre}¥${detail.value}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = valueColor
                        )
                    }
                }
            }
        }
    }

}


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
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row {
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

