package app.allever.android.compose.flow.money.data

data class DailyRecordItem(
    val title: String,
    val week: String,
    val income: Float,
    val outcome: Float,
    val list: MutableList<DailyDetail> = mutableListOf()
)

data class DailyDetail(
    val icon: Int,
    val title: String,
    val sort: String,
    val time: String,
    val pay: String,
    val value: Float,
    val type: Int
)

class Type {
    companion object {
        const val INCOME = 0
        const val OUTCOME = 1
    }
}