package com.jesil.projectmvi.mviapplication.utils

sealed class MainStateEvent {
    object GetBlogEvent : MainStateEvent()
    object None: MainStateEvent()
}