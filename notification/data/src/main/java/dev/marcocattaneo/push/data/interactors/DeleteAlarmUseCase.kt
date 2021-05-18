package dev.marcocattaneo.push.data.interactors

import dev.marcocattaneo.gasprice.common.interactor.AbsUseCase
import dev.marcocattaneo.push.data.di.AlarmApiScope
import dev.marcocattaneo.push.data.repositories.AlarmsRepository
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    @AlarmApiScope val alarmsRepository: AlarmsRepository
) : AbsUseCase<String, Unit>() {

    override suspend fun execute(input: String) {
        alarmsRepository.delete(input)
    }

}