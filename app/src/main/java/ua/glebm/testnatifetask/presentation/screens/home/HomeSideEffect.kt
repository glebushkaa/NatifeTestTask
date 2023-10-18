package ua.glebm.testnatifetask.presentation.screens.home

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

sealed class HomeSideEffect {

    data object ScrollToTop : HomeSideEffect()

    data class NavigateToFullscreen(
        val uniqueId: String,
        val searchQuery: String,
    ) : HomeSideEffect()

    inline fun handle(
        navigateToFullscreen: (String, String) -> Unit = { _, _ -> },
        scrollToTop: () -> Unit = {},
    ) {
        when (this) {
            is NavigateToFullscreen -> navigateToFullscreen(uniqueId, searchQuery)
            ScrollToTop -> scrollToTop()
        }
    }
}
