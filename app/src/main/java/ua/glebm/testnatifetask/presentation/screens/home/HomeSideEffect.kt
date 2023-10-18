package ua.glebm.testnatifetask.presentation.screens.home

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/17/2023
 */

sealed class HomeSideEffect {

    data object ScrollToTop : HomeSideEffect()

    data class NavigateToFullscreen(
        val uniqueId: String,
        val searchQuery: String,
        val position: Int,
    ) : HomeSideEffect()

    inline fun handle(
        navigateToFullscreen: (String, String, Int) -> Unit = { _, _, _ -> },
        scrollToTop: () -> Unit = {},
    ) {
        when (this) {
            is NavigateToFullscreen -> navigateToFullscreen(uniqueId, searchQuery, position)
            ScrollToTop -> scrollToTop()
        }
    }
}
