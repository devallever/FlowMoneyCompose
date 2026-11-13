package app.allever.android.compose.flow.money.lib

import android.widget.Toast

fun toast(msg: String) {
    Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
}
