package com.example.dynadroid.ui.on_boarding.select_apps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynadroid.data.mappers.toAppInfo
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledAppsRepository
import com.example.dynadroid.utils.AppLoadResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class AppListState(
    val isLoading: Boolean = false,
    val apps: List<AppInfo> = emptyList(),
    val errorMessage: String? = null,
    val loadingProgress: Float = 0f,
    val isSearchAppClicked: Boolean = false
)

class InstalledAppsListViewModel(private val repository: InstalledAppsRepository) : ViewModel() {

    var uiState by mutableStateOf(AppListState())
        private set

    private fun loadApps() {
        viewModelScope.launch {
            repository.getSyncedApps()
                .onStart {
                    uiState = uiState.copy(isLoading = true, loadingProgress = 0f)
                }
                .catch { e ->
                    uiState = uiState.copy(
                        isLoading = false,
                        apps = emptyList(),
                        errorMessage = e.localizedMessage ?: "Unknown Error"
                    )
                }
                .collect { result ->
                    uiState = when (result) {
                        is AppLoadResult.Progress -> {
                            uiState.copy(loadingProgress = result.percentage)

                        }

                        is AppLoadResult.Success -> {
                            uiState.copy(
                                isLoading = false,
                                apps = result.data.map { it.toAppInfo() },
                                loadingProgress = 1f
                            )
                        }
                    }
                }
        }
    }

    fun searchApp() {
        uiState = uiState.copy(isSearchAppClicked = true)
        loadApps()
    }

}