package com.gunt.kakaosearchrevision.ui

sealed class BookListEvent {
    object NewSearchEvent : BookListEvent()
    object NextPageEvent : BookListEvent()
    object RestoreStateEvent : BookListEvent()
}