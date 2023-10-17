package ua.glebm.testnatifetask.core.android.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

fun Context.toast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT,
) {
    Toast.makeText(this, message, duration).show()
}
