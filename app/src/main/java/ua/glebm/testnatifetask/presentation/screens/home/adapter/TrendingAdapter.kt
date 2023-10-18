package ua.glebm.testnatifetask.presentation.screens.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ua.glebm.testnatifetask.core.android.BasePagingAdapter
import ua.glebm.testnatifetask.core.android.BaseViewHolder
import ua.glebm.testnatifetask.databinding.ItemGifBinding
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class TrendingAdapter(
    private val onGifClick: (String) -> Unit,
) : BasePagingAdapter<Gif, GifViewHolder>(
    GifDiffUtilCallback,
) {
    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<Gif> {
        val binding = ItemGifBinding.inflate(inflater, parent, false)
        return GifViewHolder(
            binding = binding,
            onGifClick = onGifClick,
        )
    }
}
