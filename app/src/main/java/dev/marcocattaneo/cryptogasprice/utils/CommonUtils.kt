package dev.marcocattaneo.cryptogasprice.utils

object CommonUtils {

    fun convertSecondsToMinutes(seconds: Int): Pair<Int, Int> {
        val m: Int = seconds.div(60)
        val s = seconds - (m * 60)
        return Pair(m, s)
    }

}