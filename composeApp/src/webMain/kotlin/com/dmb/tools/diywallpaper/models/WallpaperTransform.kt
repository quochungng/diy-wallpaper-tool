package com.dmb.tools.diywallpaper.models

data class WallpaperTransform(
    val width: UnitValue,
    val height: UnitValue,
    val positionX: UnitValue,
    val positionY: UnitValue,
    val rotation: Float,
    val scaleX: Float,
    val scaleY: Float,
    val pivotX: UnitValue,
    val pivotY: UnitValue,
)