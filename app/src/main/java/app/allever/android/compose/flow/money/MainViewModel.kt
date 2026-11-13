package app.allever.android.compose.flow.money

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var pageState = mutableIntStateOf(0)

    val navigationList = listOf("明细", "统计", "资产", "设置")
}