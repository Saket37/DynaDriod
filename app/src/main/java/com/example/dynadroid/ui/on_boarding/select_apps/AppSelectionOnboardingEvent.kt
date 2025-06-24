package com.example.dynadroid.ui.on_boarding.select_apps

sealed interface AppSelectionOnboardingEvent {
    object OnSelectAppClick : AppSelectionOnboardingEvent
    object OnNextClick : AppSelectionOnboardingEvent
}