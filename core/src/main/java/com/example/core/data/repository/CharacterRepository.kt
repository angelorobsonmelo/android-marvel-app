package com.example.core.data.repository

import androidx.paging.PagingSource

interface CharacterRepository {

    fun getCharacters(query: String): PagingSource<Int, com.example.core.domain.model.Character>
}