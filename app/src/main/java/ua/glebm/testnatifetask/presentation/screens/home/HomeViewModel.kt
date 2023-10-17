package ua.glebm.testnatifetask.presentation.screens.home

import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ua.glebm.testnatifetask.core.android.BaseViewModel
import ua.glebm.testnatifetask.core.android.stateReducerFlow
import ua.glebm.testnatifetask.domain.usecase.gif.GetTrendingFlowUseCase
import ua.glebm.testnatifetask.model.Gif
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingFlowUseCase: GetTrendingFlowUseCase,
) : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = HomeState(),
        reduceState = ::reduce,
    )

    val trendingGifs: Flow<PagingData<Gif>>
        get() = getTrendingFlowUseCase()
            .getOrDefault(MutableSharedFlow())
            .cachedIn(viewModelScope)

    private fun reduce(
        currentState: HomeState,
        action: HomeAction,
    ): HomeState {
        return currentState
    }
}
