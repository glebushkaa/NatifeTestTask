package ua.glebm.testnatifetask.presentation.screens.fullscreen.adapter

import com.bumptech.glide.Glide
import ua.glebm.testnatifetask.core.android.BaseViewHolder
import ua.glebm.testnatifetask.core.android.extensions.gone
import ua.glebm.testnatifetask.core.android.extensions.loadListener
import ua.glebm.testnatifetask.core.android.extensions.visible
import ua.glebm.testnatifetask.databinding.ItemFullscreenGifBinding
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

class FullscreenGifViewHolder(
    private val binding: ItemFullscreenGifBinding,
) : BaseViewHolder<Gif>(binding.root) {

    override fun bind(model: Gif?) = with(binding) {
        progressGif.visible()
        if (model == null) return@with
        val listener = loadListener(
            onResourceReady = { _, _, _, _, _ -> progressGif.gone() },
        )
        imgFullscreenGif.layout(0, 0, 0, 0)
        Glide.with(root)
            .load(model.url)
            .addListener(listener)
            .fitCenter()
            .centerCrop()
            .into(imgFullscreenGif)
    }
}
