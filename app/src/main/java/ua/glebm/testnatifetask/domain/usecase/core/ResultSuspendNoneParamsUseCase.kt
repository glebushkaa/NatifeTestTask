package ua.glebm.testnatifetask.domain.usecase.core

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

abstract class ResultSuspendNoneParamsUseCase<out Type : Any>(
    useCaseLogger: UseCaseLogger,
) : ResultSuspendUseCase<Type, UseCase.Params>(useCaseLogger) {

    abstract suspend operator fun invoke(): Result<Type>
    override suspend fun invoke(params: UseCase.Params) = invoke()
}
