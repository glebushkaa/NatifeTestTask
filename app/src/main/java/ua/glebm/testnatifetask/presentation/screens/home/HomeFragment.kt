package ua.glebm.testnatifetask.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.glebm.testnatifetask.core.android.BaseFragment
import ua.glebm.testnatifetask.data.mapper.toGif
import ua.glebm.testnatifetask.data.network.GiphyApi
import ua.glebm.testnatifetask.databinding.FragmentHomeBinding
import ua.glebm.testnatifetask.log.debug
import ua.glebm.testnatifetask.presentation.screens.home.adapter.TrendingAdapter
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
) {

    override val viewModel: HomeViewModel by viewModels()

    private val trendingAdapter: TrendingAdapter
        get() = binding.recyclerTrending.adapter as TrendingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerTrending.adapter = TrendingAdapter()

        viewModel.trendingGifs.onEach {
            trendingAdapter.submitData(it)
        }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        binding.recyclerTrending.adapter = null
        super.onDestroyView()
    }
}
