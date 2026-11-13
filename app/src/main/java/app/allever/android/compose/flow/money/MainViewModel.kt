package app.allever.android.compose.flow.money

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import app.allever.android.compose.flow.money.data.SortItem

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
}
