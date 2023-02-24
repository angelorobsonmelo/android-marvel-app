package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.CharacterRepository
import com.example.core.domain.model.Character
import com.example.core.usecase.GetCharactersUseCase.GetCharacterParams
import com.example.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharacterParams): Flow<PagingData<Character>>

    data class GetCharacterParams(val query: String, val pagingConfig: PagingConfig)
}

class GetCharactersUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : PagingUseCase<GetCharacterParams, Character>(),
    GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharacterParams): Flow<PagingData<Character>> {
        val pagingSource = characterRepository.getCharacters(params.query)
        return Pager(config = params.pagingConfig) { pagingSource }.flow
    }

}