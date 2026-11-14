package app.allever.android.compose.flow.money.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.allever.android.compose.flow.money.data.WeeklyBarData

@Composable
fun WeeklyExpenseBarChart(
    items: List<WeeklyBarData>,
    baselineValue: Float,
    baselineLabel: String = "日均基准",
    modifier: Modifier = Modifier,
    maxBarHeight: Dp = 120.dp, // 适当控制最大柱高，给顶部留出充足呼吸感
    barWidth: Dp = 44.dp,
    animationDurationMillis: Int = 750,
    onBarSelected: ((index: Int, item: WeeklyBarData) -> Unit)? = null
) {
    var selectedIndex by remember(items) {
        val defaultPeakIndex = items.indexOfFirst { it.isPeak }
        mutableStateOf(if (defaultPeakIndex >= 0) defaultPeakIndex else -1)
    }

    val growthProgress = remember { Animatable(0f) }
    LaunchedEffect(items) {
        growthProgress.snapTo(0f)
        growthProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = animationDurationMillis,
                easing = FastOutSlowInEasing
            )
        )
    }

    val maxDataValue = maxOf(
        items.maxOfOrNull { it.value } ?: 0f,
        baselineValue
    ).coerceAtLeast(1f)

    // 固定顶部文字+徽章预留高度（约 56.dp）和底部 X 轴标签高度（28.dp），防止任何列溢出
    val topHeaderReserveHeight = 58.dp
    val xAxisLabelAreaHeight = 28.dp

    val normalBarColor = Color(0xFF4F8E76)
    val highlightBarColor = Color(0xFF1B663E)
    val normalTextColor = Color(0xFF555E5A)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(maxBarHeight + topHeaderReserveHeight + xAxisLabelAreaHeight)
    ) {
        // 1. 底层：横向基准虚线与右侧标签
        val baselineRatio = (baselineValue / maxDataValue).coerceIn(0f, 1f)
        val baselineOffsetFromBottom = xAxisLabelAreaHeight + (maxBarHeight * baselineRatio)

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = baselineOffsetFromBottom)
                .fillMaxWidth()
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.BottomCenter)
            ) {
                drawLine(
                    color = Color(0xFFCBD5D0),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(10f, 10f),
                        phase = 0f
                    )
                )
            }

            Text(
                text = baselineLabel,
                fontSize = 11.sp,
                color = Color(0xFF8C9691),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 4.dp)
            )
        }

        // 2. 上层：柱状图主体
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex
                val targetHeightRatio = (item.value / maxDataValue).coerceIn(0.05f, 1f)
                val animatedBarHeight = maxBarHeight * targetHeightRatio * growthProgress.value

                val barColor by animateColorAsState(
                    targetValue = if (isSelected) highlightBarColor else normalBarColor,
                    animationSpec = tween(durationMillis = 250),
                    label = "barColor"
                )
                val labelColor by animateColorAsState(
                    targetValue = if (isSelected) highlightBarColor else normalTextColor,
                    animationSpec = tween(durationMillis = 250),
                    label = "labelColor"
                )

                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            selectedIndex = index
                            onBarSelected?.invoke(index, item)
                        }
                        .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // 选中态胶囊徽章（调整内边距，使其更精致不显臃肿）
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(highlightBarColor)
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (item.isPeak) "峰值" else "已选",
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                    }

                    // 柱子上方金额数值
                    Text(
                        text = item.formattedValue,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = labelColor,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 顶部圆角柱体
                    Box(
                        modifier = Modifier
                            .width(barWidth)
                            .height(animatedBarHeight)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 12.dp,
                                    topEnd = 12.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 0.dp
                                )
                            )
                            .background(barColor)
                    )

                    // 底部 X 轴标签（所有列严格保持完全一致的底部基准线）
                    Box(
                        modifier = Modifier.height(xAxisLabelAreaHeight),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                            color = labelColor,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyExpenseBarChartPreview() {
    val weeklyData = listOf(
        WeeklyBarData(label = "第1周", value = 1120f, formattedValue = "¥1,120"),
        WeeklyBarData(label = "第2周", value = 980f,  formattedValue = "¥980"),
        WeeklyBarData(label = "第3周", value = 1460f, formattedValue = "¥1,460", isPeak = true),
        WeeklyBarData(label = "第4周", value = 820f,  formattedValue = "¥820")
    )

    Box(modifier = Modifier.padding(20.dp)) {
        WeeklyExpenseBarChart(
            items = weeklyData,
            baselineValue = 1095f,
            onBarSelected = { index, item ->
                // 可在此处联动更新页面其他数据，如展示该周消费明细
            }
        )
    }
}