package dev.marcocattaneo.gasprice.data.interactors

import dev.marcocattaneo.gasprice.data.CoroutineTestRule
import dev.marcocattaneo.gasprice.data.mappers.PriceMapper
import dev.marcocattaneo.gasprice.domain.models.FirestoreDate
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketException

@ExperimentalCoroutinesApi
class GetLatestPriceUseCaseTest {

    lateinit var getLatestPriceUseCase: GetLatestPriceUseCase

    @MockK
    lateinit var gasPriceRepository: GasPriceRepository

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getLatestPriceUseCase = GetLatestPriceUseCase(gasPriceRepository, PriceMapper())
    }

    @Test
    fun `Test get gas price with failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceRepository.getGasPrice() } throws SocketException("Api error")

        // When
        val errorOccurred = try {
            getLatestPriceUseCase.execute(null)
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
    }

    @Test
    fun `Test get gas price`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceRepository.getGasPrice() } returns GasPrice(
            400000000000L,
            300000000000L,
            100000000000L,
            50000000000L,
            FirestoreDate(0L, 0L)
        )

        // When
        val result = getLatestPriceUseCase.execute(null)

        // Then
        assertNotNull(result)
        assertEquals(50, result.slow.price)
        assertEquals(300, result.fast.price)
        assertEquals(400, result.fastest.price)
        assertEquals(0, result.lastUpdate.time)
    }

}