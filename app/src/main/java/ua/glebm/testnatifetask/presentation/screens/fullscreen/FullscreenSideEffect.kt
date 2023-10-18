package ua.glebm.testnatifetask.presentation.screens.fullscreen

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

sealed class FullscreenSideEffect {

    data object ScrollToStart : FullscreenSideEffect()

    inline fun handle(
        scrollToStart: () -> Unit = {},
    ) {
        when (this) {
            is ScrollToStart -> scrollToStart()
        }
    }
}
