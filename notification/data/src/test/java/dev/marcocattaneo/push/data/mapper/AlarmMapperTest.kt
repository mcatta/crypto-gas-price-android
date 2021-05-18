package dev.marcocattaneo.push.data.mapper

import dev.marcocattaneo.gasprice.common.models.FirestoreDate
import dev.marcocattaneo.push.data.models.Alarm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class AlarmMapperTest {

    private val mapper = AlarmMapper()

    @Test
    fun `Test mapping`() {
        // Given
        val alarm = Alarm(
            id = "id",
            limit = 20.42,
            userId = "uuid",
            enabled = true,
            createdAt = FirestoreDate(10L, 10000L),
            updatedAt = FirestoreDate(10L, 10000L)
        )

        // When
        val mapped = mapper.parseFromTo(alarm)

        // Then
        assertEquals("id", mapped.id)
        assertEquals(true, mapped.enabled)
        assertEquals(20.42, mapped.limit, 0.0)
    }

    @Test
    fun `Test mapping exception`() {
        // Given
        val alarm = Alarm(
            id = null,
            limit = 20.42,
            userId = "uuid",
            enabled = true,
            createdAt = FirestoreDate(10L, 10000L),
            updatedAt = FirestoreDate(10L, 10000L)
        )

        // When
        val errorOccurred = try {
            mapper.parseFromTo(alarm)
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
    }

}