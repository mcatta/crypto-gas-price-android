package dev.marcocattaneo.push.data.interactors

import dev.marcocattaneo.gasprice.common.interactor.AbsUseCase
import dev.marcocattaneo.push.data.di.AlarmApiScope
import dev.marcocattaneo.push.data.repositories.AlarmsRepository
import javax.inject.Inject

class CreateAlarmUseCase @Inject constructor(
    @AlarmApiScope val alarmsRepository: AlarmsRepository
): AbsUseCase<Double, Unit>() {
    override suspend fun execute(input: Double) {
        alarmsRepository.create(input)
    }
}