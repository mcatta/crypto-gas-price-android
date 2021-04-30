package dev.marcocattaneo.gasprice.data.interactors

import dev.marcocattaneo.gasprice.data.CoroutineTestRule
import dev.marcocattaneo.gasprice.data.mappers.PriceMapper
import dev.marcocattaneo.gasprice.domain.models.FirestoreDate
import dev.marcocattaneo.gasprice.domain.models.GasPrice
import dev.marcocattaneo.gasprice.domain.repositories.GasPriceRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.SocketException

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class GetGasHistoriesUseCaseTest {

    lateinit var getGasHistoriesUseCase: GetGasHistoriesUseCase

    @MockK
    lateinit var gasPriceRepository: GasPriceRepository

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getGasHistoriesUseCase = GetGasHistoriesUseCase(gasPriceRepository, PriceMapper())
    }

    @Test
    fun `Test get gas price with failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceRepository.getGasHistory() } throws SocketException("Api error")

        // When
        val errorOccurred = try {
            getGasHistoriesUseCase.execute(null)
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
        coEvery { gasPriceRepository.getGasHistory() } returns listOf(
            GasPrice(
                400000000000L,
                300000000000L,
                100000000000L,
                50000000000L,
                FirestoreDate(0L, 0L)
            )
        )

        // When
        val result = getGasHistoriesUseCase.execute(null)

        // Then
        assertNotNull(result)
        result.first().let {
            assertEquals(50, it.slow.price)
            assertEquals(300, it.fast.price)
            assertEquals(400, it.fastest.price)
            assertEquals(0, it.lastUpdate.time)
        }

    }

}