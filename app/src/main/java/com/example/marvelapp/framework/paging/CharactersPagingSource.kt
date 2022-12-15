package com.example.marvelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.repository.CharacterRemoteDataSource
import com.example.core.domain.model.Character
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import com.example.marvelapp.framework.network.response.toCharacterModel
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val offSet = params.key ?: 0

            val queries = hashMapOf(
                "offset" to offSet.toString()
            )

            if (query.isNotEmpty()) {
                queries["nameStartWith"] = query
            }

            val response = remoteDataSource.fetchCharacters(queries)

            val responseOffset = response.data.offSet
            val totalCharacters = response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
                    responseOffset + LIMIT
                } else null

            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 20
    }

}