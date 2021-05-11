package dev.marcocattaneo.gasprice.data.mappers

import dev.marcocattaneo.gasprice.common.models.FirestoreDate
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PriceMapperTest {

    private val mapper = PriceMapper()

    @Test
    fun `Test Mapper`() {
        // Given
        val model = GasPrice(
            rapid = 30000000000L,
            fast = 20000000000L,
            standard = 10000000000L,
            slow = 5000000000L,
            createdAt = FirestoreDate(10L, 10000L)
        )

        // When
        val mapped = mapper.parseFromTo(model)

        // Then
        assertEquals(30, mapped.fastest.price)
        assertEquals(30, mapped.fastest.speedSeconds)
        assertEquals(20, mapped.fast.price)
        assertEquals(120, mapped.fast.speedSeconds)
        assertEquals(5, mapped.slow.price)
        assertEquals(300, mapped.slow.speedSeconds)
    }

}