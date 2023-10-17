package ua.glebm.testnatifetask.presentation.screens.home.adapter

import com.bumptech.glide.Glide
import ua.glebm.testnatifetask.core.android.BaseViewHolder
import ua.glebm.testnatifetask.core.android.extensions.gone
import ua.glebm.testnatifetask.core.android.extensions.loadListener
import ua.glebm.testnatifetask.core.android.extensions.visible
import ua.glebm.testnatifetask.databinding.ItemGifBinding
import ua.glebm.testnatifetask.model.Gif

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class GifViewHolder(
    private val binding: ItemGifBinding,
) : BaseViewHolder<Gif>(binding.root) {

    override fun bind(model: Gif?): Unit = with(binding) {
        progressGif.visible()
        if (model == null) return@with
        val listener = loadListener(
            onResourceReady = { _, _, _, _, _ -> progressGif.gone() },
        )
        imgGif.layout(0, 0, 0, 0)
        Glide.with(root)
            .load(model.url)
            .addListener(listener)
            .fitCenter()
            .centerCrop()
            .into(imgGif)
    }
}
