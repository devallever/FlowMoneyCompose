package app.allever.android.compose.flow.money.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.allever.android.compose.flow.money.data.LegendItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpenseLegendFlow(
    items: List<LegendItem>,
    modifier: Modifier = Modifier
) {
    // 直接使用 FlowRow 作为根容器，背景透明且自动居中换行
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items.forEach { item ->
            LegendChip(item = item)
        }
    }
}

@Composable
private fun LegendChip(item: LegendItem) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color(0xFFEAF3EE)) // 保留每个标签自身的浅薄荷绿胶囊底色
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // 左侧彩色圆点
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(item.dotColor)
        )

        // 右侧文字
        Text(
            text = "${item.label} ${item.percentageText}",
            fontSize = 13.sp,
            color = Color(0xFF1A221E),
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseLegendFlowPreview() {
    val legendItems = listOf(
        LegendItem("餐饮", "38%", Color(0xFF1A653E)),
        LegendItem("购物", "24%", Color(0xFFF6B554)),
        LegendItem("住房", "18%", Color(0xFF66CE9C)),
        LegendItem("交通", "12%", Color(0xFF287C52)),
        LegendItem("其它", "8%",  Color(0xFFCBD6D0))
    )

    ExpenseLegendFlow(
        items = legendItems,
        modifier = Modifier.padding(16.dp)
    )
}

