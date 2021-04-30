package dev.marcocattaneo.gasprice.data.models

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class UIGasPriceTest {

    @Test
    fun `Test UIGasPriceTest`() {
        // Given
        val model = UIGasPrice(
            fastest = UIGasPrice.Item(
                price = 12,
                speedSeconds = 90
            ),
            slow = UIGasPrice.Item(
                price = 12,
                speedSeconds = 90
            ),
            fast = UIGasPrice.Item(
                price = 12,
                speedSeconds = 90
            ),
            lastUpdate = Date()
        )

        // Then
        assertNotNull(model.fast)
        assertNotNull(model.fastest)
        assertNotNull(model.slow)
        assertNotNull(model.lastUpdate)
    }

}