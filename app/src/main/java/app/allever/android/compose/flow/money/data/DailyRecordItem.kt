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
    val type: Int,
    val payType: String
)

class Type {
    companion object {
        const val INCOME = 0
        const val OUTCOME = 1
    }
}

class PayType {
    companion object {
        const val CASH = "现金"
        const val BANK = "银行"
        const val ALIPAY = "支付宝"
        const val WECHAT = "微信"
    }
}