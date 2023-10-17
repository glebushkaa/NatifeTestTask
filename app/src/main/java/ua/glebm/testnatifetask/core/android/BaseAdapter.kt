package ua.glebm.testnatifetask.core.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ua.glebm.testnatifetask.log.debug

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    diffUtilItemCallback: DiffUtil.ItemCallback<T>,
) : ListAdapter<T, BaseViewHolder<T>>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        createViewHolder(getLayoutInflater(parent), parent, viewType)

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    protected abstract fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<T>

    private fun getLayoutInflater(viewGroup: ViewGroup) = LayoutInflater.from(
        viewGroup.context,
    )
}
