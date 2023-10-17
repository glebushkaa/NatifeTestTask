package ua.glebm.testnatifetask.core.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(model: T?)
}
