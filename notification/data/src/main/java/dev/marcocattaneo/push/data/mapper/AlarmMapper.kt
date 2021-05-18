package dev.marcocattaneo.push.data.mapper

import dev.marcocattaneo.gasprice.common.mapper.IMapper
import dev.marcocattaneo.push.data.models.Alarm
import dev.marcocattaneo.push.data.models.UIAlarm
import javax.inject.Inject

class AlarmMapper @Inject constructor(): IMapper<Alarm, UIAlarm> {

    override fun parseFromTo(from: Alarm): UIAlarm = UIAlarm(
        id = from.id ?: throw IllegalStateException("Id must not be null"),
        limit = from.limit,
        enabled = from.enabled
    )

    override fun parseToFrom(to: UIAlarm): Alarm {
        throw IllegalStateException("Not supported yet")
    }
}