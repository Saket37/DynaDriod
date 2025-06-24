package com.example.dynadroid.ui.on_boarding.select_apps

sealed interface AppSelectionScreenEvent {
    object OnSelectAppClick : AppSelectionScreenEvent
    object OnNextClick : AppSelectionScreenEvent
}