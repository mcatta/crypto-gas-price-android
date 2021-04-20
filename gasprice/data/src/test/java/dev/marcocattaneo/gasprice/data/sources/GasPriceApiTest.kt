package dev.marcocattaneo.gasprice.data.sources

import dev.marcocattaneo.gasprice.data.CoroutineTestRule
import dev.marcocattaneo.gasprice.data.services.GasPriceService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketException

@ExperimentalCoroutinesApi
class GasPriceApiTest {

    lateinit var garPriceApi: GasPriceApi

    @MockK
    lateinit var gasPriceService: GasPriceService

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        garPriceApi = GasPriceApi(gasPriceService)
    }

    @Test
    fun `Test getGasPrice() with failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getLatest() } throws SocketException("Api error")

        // When
        val errorOccurred = try {
            garPriceApi.getGasPrice()
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
    }

    @Test
    fun `Test getGasPrice() with result`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getLatest() } returns mockk()

        // When
        val result = garPriceApi.getGasPrice()

        // Then
        assertNotNull(result)
    }

    @Test
    fun `Test getGasHistory() with failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getHistories() } throws SocketException("Api error")

        // When
        val errorOccurred = try {
            garPriceApi.getGasHistory()
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
    }

    @Test
    fun `Test getGasHistory() with result`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getHistories() } returns listOf()

        // When
        val result = garPriceApi.getGasHistory()

        // Then
        assertNotNull(result)
    }

}