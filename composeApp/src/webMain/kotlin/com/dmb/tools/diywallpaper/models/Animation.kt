package com.dmb.tools.diywallpaper.models

sealed interface Animation {
    val duration: Long
    val repeatCount: Int

    data class Translation(
        val fromX: UnitValue,
        val toX: UnitValue,
        val fromY: UnitValue,
        val toY: UnitValue,
        override val duration: Long,
        override val repeatCount: Int,
    ): Animation

    data class Rotation(
        val fromAngle: Float,
        val toAngle: Float,
        val pivotX: UnitValue,
        val pivotY: UnitValue,
        override val duration: Long,
        override val repeatCount: Int,
    ): Animation
}