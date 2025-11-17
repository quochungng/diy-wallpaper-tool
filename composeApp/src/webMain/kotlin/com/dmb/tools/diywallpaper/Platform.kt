package com.dmb.tools.diywallpaper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform