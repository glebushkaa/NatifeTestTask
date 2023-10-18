package ua.glebm.testnatifetask.presentation.screens.fullscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ua.glebm.testnatifetask.core.android.BasePagingAdapter
import ua.glebm.testnatifetask.core.android.BaseViewHolder
import ua.glebm.testnatifetask.databinding.ItemFullscreenGifBinding
import ua.glebm.testnatifetask.model.Gif
import ua.glebm.testnatifetask.presentation.screens.home.adapter.GifDiffUtilCallback
import ua.glebm.testnatifetask.presentation.screens.home.adapter.GifViewHolder

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

class FullscreenAdapter : BasePagingAdapter<Gif, GifViewHolder>(
    GifDiffUtilCallback,
) {
    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<Gif> {
        val binding = ItemFullscreenGifBinding.inflate(inflater, parent, false)
        return FullscreenGifViewHolder(binding)
    }
}
