package com.dmb.tools.diywallpaper.models

sealed interface ContentSource {
    data class Image(val url: String) : ContentSource
    data class Text(val text: String) : ContentSource
}