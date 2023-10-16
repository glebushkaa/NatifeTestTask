package ua.glebm.testnatifetask.domain.usecase.core

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

abstract class ResultNoneParamsUseCase<out Type : Any>(
    useCaseLogger: UseCaseLogger,
) : ResultUseCase<Type, UseCase.Params>(useCaseLogger) {

    abstract operator fun invoke(): Result<Type>
    override fun invoke(params: UseCase.Params) = invoke()
}
