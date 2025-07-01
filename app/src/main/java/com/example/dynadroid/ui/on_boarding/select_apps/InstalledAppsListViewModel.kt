package com.example.dynadroid.ui.on_boarding.select_apps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynadroid.data.mappers.toAppInfo
import com.example.dynadroid.data.mappers.toSelectedAppInfo
import com.example.dynadroid.data.model.AppInfo
import com.example.dynadroid.domain.InstalledAppsRepository
import com.example.dynadroid.utils.AppLoadResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AppListState(
    val isLoading: Boolean = false,
    val apps: List<AppInfo> = emptyList(),
    val errorMessage: String? = null,
    val loadingProgress: Float = 0f,
    val isSearchAppClicked: Boolean = false
)

class InstalledAppsListViewModel(private val repository: InstalledAppsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AppListState())

    val uiState: StateFlow<AppListState> = _uiState

    private val modifiedAppPackages = mutableSetOf<String>()


    private fun loadApps() {
        viewModelScope.launch {
            repository.getSyncedApps()
                .onStart {
                    _uiState.update {
                        it.copy(isLoading = true, loadingProgress = 0f)
                    }
                }
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            apps = emptyList(),
                            errorMessage = e.localizedMessage ?: "Unknown Error"
                        )
                    }
                }
                .collect { result ->
                    when (result) {
                        is AppLoadResult.Progress -> {
                            _uiState.update {
                                it.copy(loadingProgress = result.percentage)
                            }

                        }

                        is AppLoadResult.Success -> {
                            // TODO add the app icon for select all
                            val appsList = mutableListOf<AppInfo>()
                            appsList.add(
                                0,
                                AppInfo(
                                    "Select All",
                                    appIcon = null,
                                    packageName = "all",
                                    isChecked = false
                                )
                            )
                            val allApps = result.data.map { it.toAppInfo() }
                            appsList.addAll(allApps)
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    apps = appsList.toList(),
                                    loadingProgress = 1f
                                )
                            }
                        }
                    }
                }
        }
    }

    fun searchApp() {
        _uiState.update {
            it.copy(isSearchAppClicked = true)
        }
        loadApps()
    }

    fun updateAppsState(packageName: String, checkedValue: Boolean) {
        if (packageName == "all") {

            if (checkedValue) {
                modifiedAppPackages.addAll(_uiState.value.apps.map { it.packageName })
                modifiedAppPackages.remove("all")
            } else {
                modifiedAppPackages.clear()
            }
            _uiState.update {
                it.copy(
                    apps = it.apps.map { app ->
                        app.copy(isChecked = checkedValue)
                    }
                )
            }
        } else {
            val updatedApps = _uiState.value.apps.toMutableList()
            val appIndex = updatedApps.indexOfFirst { it.packageName == packageName }
            if (appIndex != -1) {
                updatedApps[appIndex] = updatedApps[appIndex].copy(isChecked = checkedValue)
            }

            val allRealApps = updatedApps.filter { it.packageName != "all" }
            val areAllRealAppsChecked = allRealApps.isNotEmpty() && allRealApps.all { it.isChecked }

            val selectAllIndex = updatedApps.indexOfFirst { it.packageName == "all" }
            if (selectAllIndex != -1) {
                updatedApps[selectAllIndex] =
                    updatedApps[selectAllIndex].copy(isChecked = areAllRealAppsChecked)
            }

            modifiedAppPackages.add(packageName)

            _uiState.update { it.copy(apps = updatedApps) }
        }
    }

    fun saveChangesToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val appsToUpdate = _uiState.value.apps.filter {
                modifiedAppPackages.contains(it.packageName)
            }
            if (appsToUpdate.isNotEmpty()) {
                repository.updateApps(packages = appsToUpdate.map { it.toSelectedAppInfo() })
            }
        }
    }

}