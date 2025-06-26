package com.example.dynadroid.ui.installed_apps_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledDeviceApps
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class AppListState(
    val isLoading: Boolean = true,
    val apps: List<AppInfo> = emptyList(),
    val errorMessage: String? = null
)

class InstalledAppsListViewModel(private val deviceApps: InstalledDeviceApps) : ViewModel() {

    var uiState by mutableStateOf(AppListState())
        private set

     fun loadApps() {
        viewModelScope.launch {
            deviceApps.getInstalledApps()
                .onStart { uiState = uiState.copy(isLoading = true) }
                .catch { e ->
                    uiState = uiState.copy(
                        isLoading = false,
                        apps = emptyList(),
                        errorMessage = e.localizedMessage ?: "Unknown Error"
                    )
                }
                .collect { apps ->
                    uiState = uiState.copy(isLoading = false, apps = apps)
                }
        }
    }

}