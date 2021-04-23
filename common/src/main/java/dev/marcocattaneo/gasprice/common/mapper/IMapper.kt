package dev.marcocattaneo.gasprice.common.mapper

interface IMapper<FROM, TO> {

    fun parseFromTo(from: FROM): TO

    fun parseToFrom(to: TO): FROM

}