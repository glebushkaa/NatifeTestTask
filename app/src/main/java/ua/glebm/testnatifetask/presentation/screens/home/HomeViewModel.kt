@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package ua.glebm.testnatifetask.presentation.screens.home

import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ua.glebm.testnatifetask.core.android.BaseViewModel
import ua.glebm.testnatifetask.core.android.stateReducerFlow
import ua.glebm.testnatifetask.domain.repository.GifRepository
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gifRepository: GifRepository,
) : BaseViewModel() {

    private val state = stateReducerFlow(
        initialState = HomeState(),
        reduceState = ::reduce,
    )
    private val sideEffectChannel = Channel<HomeSideEffect>()
    private var queryFlow = MutableStateFlow("")

    init {
        subscribeOnSearchingGifs()
        queryFlow.tryEmit("")
    }

    fun sendAction(action: HomeAction) = viewModelScope.launch(Dispatchers.IO) {
        state.handleAction(action)
    }

    fun observe(
        onSideEffect: suspend (HomeSideEffect) -> Unit = {},
        render: suspend (HomeState) -> Unit = {},
    ) {
        sideEffectChannel.receiveAsFlow().onEach {
            onSideEffect.invoke(it)
        }.launchIn(viewModelScope)

        state.onEach {
            render.invoke(it)
        }.flowOn(Dispatchers.Main).launchIn(viewModelScope)
    }

    private fun subscribeOnSearchingGifs() = viewModelScope.launch(Dispatchers.IO) {
        queryFlow.debounce(500).flatMapLatest {
            gifRepository.getSearchingGifs(it)
        }.cachedIn(viewModelScope).collectLatest {
            val action = HomeAction.UpdateGifsList(it)
            sendAction(action)
        }
    }

    private suspend fun reduce(currentState: HomeState, action: HomeAction): HomeState {
        action.handle(
            updateSearchQuery = { query -> queryFlow.emit(query) },
            updateGifsList = { pagingData ->
                return currentState.copy(pagingGifs = pagingData)
            },
        )
        return currentState
    }
}
