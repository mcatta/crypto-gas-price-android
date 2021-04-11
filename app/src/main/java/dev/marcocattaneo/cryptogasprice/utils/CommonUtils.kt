package dev.marcocattaneo.cryptogasprice.utils

object CommonUtils {

    fun convertSecondsToMinutes(seconds: Int): Pair<Int, Int> {
        val minutes: Int = seconds.div(60)
        val seconds = seconds - (minutes * 60)
        return Pair(minutes, seconds)
    }

}