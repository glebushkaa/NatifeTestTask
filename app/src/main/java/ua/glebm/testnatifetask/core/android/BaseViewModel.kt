package ua.glebm.testnatifetask.core.android

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import ua.glebm.testnatifetask.log.error
import ua.glebm.testnatifetask.log.tag

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

abstract class BaseViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error("View model exception -> ${this@BaseViewModel.tag()}", throwable)
    }
    private val job = SupervisorJob()
    private val context = Dispatchers.Main.immediate + job + coroutineExceptionHandler

    val viewModelScope = CoroutineScope(context)

    override fun onCleared() {
        context.cancel()
        super.onCleared()
    }
}
