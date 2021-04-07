package dev.marcocattaneo.gasprice.domain.mapper

interface IMapper<FROM, TO> {

    fun parseFromTo(from: FROM): TO

    fun parseToFrom(to: TO): FROM

}