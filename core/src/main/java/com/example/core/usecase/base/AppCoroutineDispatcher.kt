package com.example.core.usecase.base

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutineDispatcher(
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher
)
