package com.example.dynadroid.di

import com.example.dynadroid.ui.on_boarding.select_apps.AppSelectionViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppSelectionViewModel)
}