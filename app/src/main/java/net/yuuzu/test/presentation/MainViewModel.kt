package net.yuuzu.test.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.yuuzu.test.domain.DataRepository
import net.yuuzu.test.domain.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    fun loadData() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when(val result = dataRepository.getData()) {
                is Resource.Success -> {
                    Log.e("TAG", "loadData: ${result.data}")
                    state = state.copy(
                        data = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        data = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}