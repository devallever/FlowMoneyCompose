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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.allever.android.compose.flow.money.MainViewModel
import app.allever.android.compose.flow.money.R
import app.allever.android.compose.flow.money.data.ChartSegment
import app.allever.android.compose.flow.money.data.LegendItem
import app.allever.android.compose.flow.money.theme.GreenDeep
import app.allever.android.compose.flow.money.theme.GreenLight
import app.allever.android.compose.flow.money.theme.GreenMiddle
import app.allever.android.compose.flow.money.ui.widget.ExpenseDonutChart
import app.allever.android.compose.flow.money.ui.widget.ExpenseLegendFlow

@Composable
fun StaticScreen(viewModel: MainViewModel, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            //tab 周 月 年
            item {
                Tab()
            }
            //selector
            item {
                Selector()
            }

            item {
                //Tips
                Tips()
            }

            item {
                PreviewIncomeOutcome()
            }

            item {
                //Chart
                Chart()
            }
        }
    }
}

@Composable
fun PreviewIncomeOutcome() {
    //card
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            //row 总支出 总收入
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
                //text 总支出 20sp
                Row(modifier = Modifier.weight(1f), verticalAlignment = CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(GreenDeep)
                    )
                    Text(
                        text = "总支出",
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                //总收入
                Row(modifier = Modifier.weight(1f), verticalAlignment = CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(GreenDeep)
                    )
                    Text(
                        text = "总收入",
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }

            //row ¥4,390.00  ¥4,390.00
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
                Text(
                    text = "¥4,390.00",
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "¥4,390.00",
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                )
            }

            //row 环比上月 -13%  月结余率 53.3%
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
                Text(
                    text = "环比上月 -13%",
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = GreenDeep,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "月结余率 53.3%",
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = GreenDeep,
                    modifier = Modifier.weight(1f)
                )
            }

            //row 储蓄目标达成率(居左)  ¥8,420.50留存(居右)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "储蓄目标达成率 53.3%",
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = GreenDeep,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = "￥8,420.50留存",
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = GreenDeep,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            //progressBar
            LinearProgressIndicator(
                progress = { 0.45f },
                trackColor = GreenMiddle,
                color = GreenDeep,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(90.dp))
            )
        }
    }
}


@Composable
fun Chart() {
    //card
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            //支出构成分布
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = CenterVertically) {
                    //icon
                    Image(
                        painter = painterResource(id = R.drawable.fm_ic_default_icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(GreenDeep)
                    )
                    Text(
                        text = "支出构成分布",
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                //text 共58笔消费
                Text(
                    text = "共58笔消费",
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            //圆形图
            val sampleSegments = listOf(
                ChartSegment(value = 38f, color = Color(0xFF1A653E)),
                ChartSegment(value = 23f, color = Color(0xFFF6B554)),
                ChartSegment(value = 18f, color = Color(0xFF66CE9C)),
                ChartSegment(value = 13f, color = Color(0xFF287C52)),
                ChartSegment(value = 8f, color = Color(0xFFCBD6D0))
            )
            ExpenseDonutChart(
                title = "本月支出",
                amountText = "¥4,380",
                statusText = "已控制在预算内",
                segments = sampleSegments,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(220.dp)
                    .align(Alignment.CenterHorizontally)
            )
            //自动换行标签
            val legendItems = listOf(
                LegendItem("餐饮", "38%", Color(0xFF1A653E)),
                LegendItem("购物", "24%", Color(0xFFF6B554)),
                LegendItem("住房", "18%", Color(0xFF66CE9C)),
                LegendItem("交通", "12%", Color(0xFF287C52)),
                LegendItem("其它", "8%", Color(0xFFCBD6D0))
            )

            ExpenseLegendFlow(
                items = legendItems, modifier = Modifier.padding(top = 30.dp).fillMaxWidth()
            )
        }
    }
}


@Composable
fun Tips() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = GreenMiddle
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.size(40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,

                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(90.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fm_ic_default_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
                    //text 支出管家提示 20sp
                    Text(
                        text = "支出管家提示",
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        modifier = Modifier.align(CenterVertically)
                    )
                    Text(
                        text = "状态极佳",
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        color = GreenDeep,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(90.dp))
                            .background(Color.White)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                //desc
                Text(
                    text = "🎉本月支出控制极佳，比上月同期节省了¥620.00，建议将结余转入定期理财账户。",
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(end = 60.dp, top = 4.dp)
                )
            }
        }

    }

}

@Composable
fun Selector() {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .background(Color(0xFFE4F6ED))
        ) {
            Image(
                painter = painterResource(id = R.drawable.fm_ic_arrow_left),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.fm_ic_calendar),
                contentDescription = null,
                colorFilter = ColorFilter.tint(GreenDeep),
                modifier = Modifier
                    .size(18.dp)
                    .align(CenterVertically)
            )
            //text date
            Text(
                text = "2023年4月",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
            )
        }

        // box image 居右
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .background(Color(0xFFE4F6ED))
        ) {
            Image(
                painter = painterResource(id = R.drawable.fm_ic_arrow_right),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun Tab() {
    var selected by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier
            .padding(
                start = 16.dp, top = 0.dp, end = 16.dp, bottom = 0.dp
            )
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(90.dp))
            .background(Color(0xFFD8E8E0))
    ) {
        Box(
            modifier = Modifier
                .padding(3.dp)
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(90.dp))
                .clickable {
                    selected = 0
                }
                .background(if (selected == 0) Color.White else Color(0x00000000))
                .align(CenterVertically), contentAlignment = Alignment.Center) {
            Text(
                text = "周",
                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if (selected == 0) GreenDeep else Color(0xFF000000),
                modifier = Modifier
            )
        }
        //周
        Box(
            modifier = Modifier
                .padding(3.dp)
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(90.dp))
                .clickable {
                    selected = 1
                }
                .background(if (selected == 1) Color.White else Color(0x00000000))
                .align(CenterVertically), contentAlignment = Alignment.Center) {
            Text(
                text = "月",
                color = if (selected == 1) GreenDeep else Color(0xFF000000),

                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }

        //年
        Box(
            modifier = Modifier
                .padding(3.dp)
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(90.dp))
                .clickable {
                    selected = 2
                }
                .background(if (selected == 2) Color.White else Color(0x00000000))
                .align(CenterVertically), contentAlignment = Alignment.Center) {
            Text(
                text = "年",
                color = if (selected == 2) GreenDeep else Color(0xFF000000),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
    }
}
