package labs.gas.musical.core.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.viewModelProvidersOf(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProviders.of(this, viewModelFactory)[T::class.java]

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
