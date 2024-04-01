package net.yuuzu.test.presentation

import net.yuuzu.test.data.ApiResponse

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: ApiResponse? = null
)
