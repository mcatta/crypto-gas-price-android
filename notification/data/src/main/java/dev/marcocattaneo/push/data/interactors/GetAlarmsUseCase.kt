package dev.marcocattaneo.push.data.interactors

import dev.marcocattaneo.gasprice.common.interactor.AbsUseCase
import dev.marcocattaneo.push.data.di.AlarmApiScope
import dev.marcocattaneo.push.data.mapper.AlarmMapper
import dev.marcocattaneo.push.data.models.UIAlarm
import dev.marcocattaneo.push.data.repositories.AlarmsRepository
import javax.inject.Inject

class GetAlarmsUseCase @Inject constructor(
    @AlarmApiScope private val alarmsRepository: AlarmsRepository,
    private val mapper: AlarmMapper
) : AbsUseCase<Nothing?, List<UIAlarm>>() {

    override suspend fun execute(input: Nothing?): List<UIAlarm> {
        return alarmsRepository.getAll()
            .sortedBy { it.createdAt._seconds }
            .map { mapper.parseFromTo(it) }
    }

}