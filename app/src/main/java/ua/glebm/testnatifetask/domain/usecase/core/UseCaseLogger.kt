package ua.glebm.testnatifetask.domain.usecase.core

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

interface UseCaseLogger {

    fun logException(tag: String, throwable: Throwable)
}
