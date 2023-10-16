package ua.glebm.testnatifetask.core.extensions

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

inline fun <reified T> List<T>.applyIf(
    predicate: Boolean,
    action: List<T>.() -> List<T>,
) = if (predicate) action(this) else this
