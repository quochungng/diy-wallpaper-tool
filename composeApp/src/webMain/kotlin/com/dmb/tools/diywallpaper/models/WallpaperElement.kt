package com.dmb.tools.diywallpaper.models

data class WallpaperElement(
    val id: String,
    val index: Int,
    val contentSource: ContentSource,
    val transform: WallpaperTransform,
    val animations: List<Animation>
)
