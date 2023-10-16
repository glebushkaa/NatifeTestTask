package ua.glebm.testnatifetask.core

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

interface StateReducerFlow<State, Action> : StateFlow<State> {

    fun handleAction(action: Action)
}
