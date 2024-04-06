package labs.gas.musical.core.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    if (android.os.Build.VERSION.SDK_INT >= 26) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.run {
            if (isActive) toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    } else {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
