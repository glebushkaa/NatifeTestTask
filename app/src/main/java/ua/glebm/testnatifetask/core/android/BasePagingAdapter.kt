package ua.glebm.testnatifetask.core.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

abstract class BasePagingAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffUtilItemCallback: DiffUtil.ItemCallback<T>,
) : PagingDataAdapter<T, BaseViewHolder<T>>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        createViewHolder(getLayoutInflater(parent), parent, viewType)

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
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
