package com.dmb.tools.diywallpaper.models

sealed class UnitValue {
    data class Value(val value: Float, val unitType: UnitType): UnitValue()
    data class Add(val a: UnitValue, val b: UnitValue): UnitValue()
    data class Subtract(val a: UnitValue, val b: UnitValue): UnitValue()
    data class Multiple(val a: UnitValue, val b: Float): UnitValue()
    data class Divide(val a: UnitValue, val b: Float): UnitValue()
}

enum class UnitType {
    DP, PX, RATIO
}