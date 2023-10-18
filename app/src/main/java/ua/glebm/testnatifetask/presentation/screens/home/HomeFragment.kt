package ua.glebm.testnatifetask.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.glebm.testnatifetask.R
import ua.glebm.testnatifetask.core.android.BaseFragment
import ua.glebm.testnatifetask.databinding.FragmentHomeBinding
import ua.glebm.testnatifetask.presentation.screens.home.adapter.TrendingAdapter

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
        setupGifsRecycler()
        setupSearch()
        viewModel.observe(
            render = ::render,
            onSideEffect = ::handleSideEffect,
        )
    }

    private suspend fun render(state: HomeState) {
        state.pagingGifs?.let { trendingAdapter.submitData(it) }
    }

    private fun handleSideEffect(sideEffect: HomeSideEffect) {
        sideEffect.handle(
            navigateToFullscreen = { uniqueId, query ->
                val bundle = Bundle().apply {
                    putString("query", query)
                    putString("uniqueId", uniqueId)
                }
                findNavController().navigate(
                    resId = R.id.action_home_to_fullscreen,
                    args = bundle,
                )
            },
        )
    }

    private fun setupGifsRecycler() = with(binding) {
        recyclerTrending.adapter = TrendingAdapter {
            val action = HomeAction.NavigateToFullscreen(it)
            viewModel.sendAction(action)
        }
    }

    private fun setupSearch() = with(binding) {
        val listener = object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String) = false

            override fun onQueryTextChange(newText: String): Boolean {
                recyclerTrending.scrollToPosition(0)
                val action = HomeAction.UpdateSearchQuery(newText)
                viewModel.sendAction(action)
                return true
            }
        }
        search.setOnQueryTextListener(listener)
    }

    override fun onDestroyView() {
        binding.recyclerTrending.adapter = null
        super.onDestroyView()
    }
}
