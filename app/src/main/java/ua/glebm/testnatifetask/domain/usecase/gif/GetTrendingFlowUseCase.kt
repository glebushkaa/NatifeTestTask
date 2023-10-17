package ua.glebm.testnatifetask.domain.usecase.gif

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.glebm.testnatifetask.domain.repository.GifRepository
import ua.glebm.testnatifetask.domain.usecase.core.ResultNoneParamsUseCase
import ua.glebm.testnatifetask.domain.usecase.core.UseCaseLogger
import ua.glebm.testnatifetask.model.Gif
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/16/2023
 */

class GetTrendingFlowUseCase @Inject constructor(
    useCaseLogger: UseCaseLogger,
    private val gifRepository: GifRepository,
) : ResultNoneParamsUseCase<Flow<PagingData<Gif>>>(useCaseLogger) {

    override fun invoke() = runCatching { gifRepository.getTrending() }
}
