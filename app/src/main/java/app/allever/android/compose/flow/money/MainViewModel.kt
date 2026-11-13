package app.allever.android.compose.flow.money

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import app.allever.android.compose.flow.money.data.DailyDetail
import app.allever.android.compose.flow.money.data.DailyRecordItem
import app.allever.android.compose.flow.money.data.SortItem
import app.allever.android.compose.flow.money.data.Type

class MainViewModel : ViewModel() {
    var pageState = mutableIntStateOf(0)

    val navigationList = listOf("明细", "统计", "资产", "设置")

    val sortListState =
        mutableStateListOf(
            SortItem(0, "全部", R.drawable.fm_ic_default_icon),
            SortItem(1, "餐饮", R.drawable.fm_ic_default_icon),
            SortItem(2, "购物", R.drawable.fm_ic_default_icon),
            SortItem(3, "交通", R.drawable.fm_ic_default_icon),
            SortItem(4, "娱乐", R.drawable.fm_ic_default_icon),
            SortItem(5, "生活", R.drawable.fm_ic_default_icon),
            SortItem(6, "工作", R.drawable.fm_ic_default_icon),
            SortItem(7, "学习", R.drawable.fm_ic_default_icon ),
            SortItem(8, "健康", R.drawable.fm_ic_default_icon),
        )
    val sortState = mutableIntStateOf(0)

    val dailyRecordList = mutableStateListOf<DailyRecordItem>().apply {
        add(DailyRecordItem("今天","星期四",  230.9f, 0f, mutableListOf(
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "早餐",
                "餐饮",
                "2023-04-07 08:00",
                "支付",
                23.0f,
                Type.OUTCOME
            ),
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "午餐",
                "餐饮",
                "2023-04-07 12:00",
                "支付",
                50.0f,
                Type.OUTCOME
            ),
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "晚餐",
                "餐饮",
                "2023-04-07 18:00",
                "支付",
                100.0f,
                Type.OUTCOME
            ),
        )))
        add(DailyRecordItem("昨天","星期三",  100.0f, 0f, mutableListOf(
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "早餐",
                "餐饮",
                "2023-04-06 08:00",
                "支付",
                23.0f,
                Type.OUTCOME
            ),
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "午餐",
                "餐饮",
                "2023-04-06 12:00",
                "支付",
                50.0f,
                Type.OUTCOME
            ),
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "晚餐",
                "餐饮",
                "2023-04-06 18:00",
                "支付",
                100.0f,
                Type.OUTCOME
            ),
        )))
        add(DailyRecordItem("10月22日","星期二",  50.0f, 0f, mutableListOf(
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "早餐",
                "餐饮",
                "2023-04-05 08:00",
                "支付",
                23.0f,
                Type.OUTCOME
            ),
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "午餐",
                "餐饮",
                "2023-04-05 12:00",
                "支付",
                50.0f,
                Type.OUTCOME
            ),
            DailyDetail(
                R.drawable.fm_ic_default_icon,
                "晚餐",
                "餐饮",
                "2023-04-05 18:00",
                "支付",
                100.0f,
                Type.OUTCOME
            ),
        )))
    }
}
