package app.allever.android.compose.flow.money

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var pageState = mutableIntStateOf(0)
}