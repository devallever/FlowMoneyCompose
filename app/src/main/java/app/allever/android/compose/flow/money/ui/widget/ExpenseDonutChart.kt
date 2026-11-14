package app.allever.android.compose.flow.money.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.allever.android.compose.flow.money.data.ChartSegment

@Composable
fun ExpenseDonutChart(
    title: String,
    amountText: String,
    statusText: String,
    segments: List<ChartSegment>,
    modifier: Modifier = Modifier.size(210.dp), // 默认尺寸，外部可通过 modifier 覆盖
    ringStrokeWidth: Dp = 28.dp
) {
    val totalValue = segments.sumOf { it.value.toDouble() }.toFloat()

    // 直接使用透明背景的 Box 作为根容器
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // 1. 底层：多段彩色圆环
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokePx = ringStrokeWidth.toPx()
            val diameter = size.minDimension - strokePx
            val topLeftOffset = Offset(
                x = (size.width - diameter) / 2f,
                y = (size.height - diameter) / 2f
            )
            val arcSize = Size(diameter, diameter)

            var currentStartAngle = -90f

            if (totalValue > 0f) {
                segments.forEach { segment ->
                    val sweepAngle = (segment.value / totalValue) * 360f
                    drawArc(
                        color = segment.color,
                        startAngle = currentStartAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = topLeftOffset,
                        size = arcSize,
                        style = Stroke(
                            width = strokePx,
                            cap = StrokeCap.Butt
                        )
                    )
                    currentStartAngle += sweepAngle
                }
            }
        }

        // 2. 顶层：中心文字与胶囊标签
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 13.sp,
                color = Color(0xFF7D8582),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = amountText,
                fontSize = 28.sp,
                color = Color(0xFF141816),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFEAF4EE),
                        shape = CircleShape
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = statusText,
                    fontSize = 11.sp,
                    color = Color(0xFF3E7B5C),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

//@Preview(showBackground = false)
@Composable
fun ExpenseDonutChartPreview() {
    val sampleSegments = listOf(
        ChartSegment(value = 38f, color = Color(0xFF1A653E)),
        ChartSegment(value = 23f, color = Color(0xFFF6B554)),
        ChartSegment(value = 18f, color = Color(0xFF66CE9C)),
        ChartSegment(value = 13f, color = Color(0xFF287C52)),
        ChartSegment(value = 8f,  color = Color(0xFFCBD6D0))
    )

    ExpenseDonutChart(
        title = "本月支出",
        amountText = "¥4,380",
        statusText = "已控制在预算内",
        segments = sampleSegments,
        modifier = Modifier.size(220.dp)
    )
}