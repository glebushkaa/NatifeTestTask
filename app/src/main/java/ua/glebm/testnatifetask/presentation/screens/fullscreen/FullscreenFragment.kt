package ua.glebm.testnatifetask.presentation.screens.fullscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.glebm.testnatifetask.core.android.BaseFragment
import ua.glebm.testnatifetask.databinding.FragmentFullscreenBinding
import ua.glebm.testnatifetask.presentation.screens.fullscreen.adapter.FullscreenAdapter

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@AndroidEntryPoint
class FullscreenFragment : BaseFragment<FragmentFullscreenBinding, FullscreenViewModel>(
    FragmentFullscreenBinding::inflate,
) {

    override val viewModel: FullscreenViewModel by viewModels()

    private val fullscreenAdapter: FullscreenAdapter
        get() = binding.pagerGif.adapter as FullscreenAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGifViewPager()
        viewModel.observe(
            onSideEffect = ::handleSideEffect,
            render = ::render,
        )
    }

    private fun setupGifViewPager() = with(binding) {
        pagerGif.adapter = FullscreenAdapter()
    }

    private fun handleSideEffect(sideEffect: FullscreenSideEffect) = with(binding) {
        sideEffect.handle(
            scrollToStart = {
            },
        )
    }

    private suspend fun render(state: FullscreenState) = withContext(Dispatchers.Main) {
        state.pagingGifs?.let {
            launch { fullscreenAdapter.submitData(it) }
        }
    }
}
