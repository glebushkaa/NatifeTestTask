package ua.glebm.testnatifetask.data.usecase

import ua.glebm.testnatifetask.domain.usecase.core.UseCaseLogger
import ua.glebm.testnatifetask.log.error
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class UseCaseLoggerImpl @Inject constructor() : UseCaseLogger {

    override fun logException(tag: String, throwable: Throwable) {
        error(tag, throwable)
    }
}
