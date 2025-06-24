package com.example.dynadroid.ui.installed_apps_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledDeviceApps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class AppListState(
    val isLoading: Boolean = true,
    val apps: List<AppInfo> = emptyList(),
    val errorMessage: String? = null
)

class InstalledAppsListViewModel(private val deviceApps: InstalledDeviceApps) : ViewModel() {

    private val _uiState = MutableStateFlow(AppListState())
    val uiState: StateFlow<AppListState> = _uiState

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            deviceApps.getInstalledApps()
                .onStart { _uiState.value = AppListState(isLoading = true) }
                .catch { e ->
                    _uiState.value = AppListState(
                        isLoading = false,
                        apps = emptyList(),
                        errorMessage = e.localizedMessage ?: "Unknown Error"
                    )
                }
                .collect { apps ->
                    _uiState.value = AppListState(isLoading = false, apps = apps)
                }
        }
    }

}