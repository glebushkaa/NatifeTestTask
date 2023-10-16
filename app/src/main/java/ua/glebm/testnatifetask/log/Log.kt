package ua.glebm.testnatifetask.log

import timber.log.Timber

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

fun error(tag: String, error: Throwable) {
    Timber.tag(tag).e(error)
}

inline fun error(tag: String, message: () -> String) {
    Timber.tag(tag).e(message())
}

inline fun error(tag: String, error: Throwable, message: () -> String) {
    Timber.tag(tag).e(error, message())
}

inline fun info(tag: String, message: () -> String) {
    Timber.tag(tag).i(message())
}

inline fun verbose(tag: String, message: () -> String) {
    Timber.tag(tag).v(message())
}

inline fun warn(tag: String, message: () -> String) {
    Timber.tag(tag).w(message())
}

inline fun debug(tag: String, message: () -> String) {
    Timber.tag(tag).d(message())
}

inline fun wtf(tag: String, message: () -> String) {
    Timber.tag(tag).wtf(message())
}

inline fun <reified T> T.tag(): String = T::class.java.simpleName
