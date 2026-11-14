package app.allever.android.compose.flow.money.data

// 单根柱状图数据模型
data class WeeklyBarData(
    val label: String,       // X轴标签，如 "第1周"
    val value: Float,        // 实际数值（用于计算柱子高度比例）
    val formattedValue: String, // 展示金额，如 "¥1,460"
    val isPeak: Boolean = false // 是否为高亮峰值项
)