package ua.glebm.testnatifetask.presentation.screens.fullscreen

import androidx.lifecycle.SavedStateHandle
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
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
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

@HiltViewModel
class FullscreenViewModel @Inject constructor(
    private val gifRepository: GifRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val state = stateReducerFlow(
        initialState = FullscreenState(),
        reduceState = ::reduce,
    )
    private val sideEffectChannel = Channel<FullscreenSideEffect>()
    private val query = savedStateHandle["query"] ?: ""
    private val uniqueId = savedStateHandle["uniqueId"] ?: ""

    init {
        subscribeOnSearchingGifs()
    }

    fun sendAction(action: FullscreenAction) = viewModelScope.launch(Dispatchers.IO) {
        state.handleAction(action)
    }

    fun observe(
        onSideEffect: suspend (FullscreenSideEffect) -> Unit = {},
        render: suspend (FullscreenState) -> Unit = {},
    ) {
        sideEffectChannel.receiveAsFlow().onEach {
            onSideEffect.invoke(it)
        }.launchIn(viewModelScope)

        state.onEach {
            render.invoke(it)
        }.flowOn(Dispatchers.Main).launchIn(viewModelScope)
    }

    private fun subscribeOnSearchingGifs() = viewModelScope.launch(Dispatchers.IO) {
        gifRepository.getSearchingGifsFromItem(
            query = query,
            uniqueId = uniqueId,
        ).cachedIn(viewModelScope).collectLatest {
            val action = FullscreenAction.UpdateGifsList(it)
            sendAction(action)
        }
    }

    private fun reduce(current: FullscreenState, action: FullscreenAction): FullscreenState {
        action.handle(
            updateGifsList = { return current.copy(pagingGifs = it) },
        )
        return current
    }
}
