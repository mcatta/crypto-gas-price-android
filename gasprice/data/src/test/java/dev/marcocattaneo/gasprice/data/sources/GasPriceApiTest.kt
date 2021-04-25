package dev.marcocattaneo.gasprice.data.sources

import dev.marcocattaneo.gasprice.common.repository.AuthenticationRepository
import dev.marcocattaneo.gasprice.data.CoroutineTestRule
import dev.marcocattaneo.gasprice.data.services.GasPriceService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketException

@ExperimentalCoroutinesApi
class GasPriceApiTest {

    private lateinit var garPriceApi: GasPriceApi

    @MockK
    lateinit var gasPriceService: GasPriceService

    @MockK
    lateinit var authenticationRepository: AuthenticationRepository

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val fakeToken = "fake-token-uuid-v4"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        garPriceApi = GasPriceApi(gasPriceService, authenticationRepository)
        coEvery { authenticationRepository.getAuthToken() } returns fakeToken
    }

    @Test
    fun `Test getGasPrice() with failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getLatest(any()) } throws SocketException("Api error")

        // When
        val errorOccurred = try {
            garPriceApi.getGasPrice()
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
        coVerify { gasPriceService.getLatest(eq("Bearer $fakeToken")) }
        coVerify { authenticationRepository.getAuthToken() }
    }

    @Test
    fun `Test getGasPrice() with result`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getLatest(any()) } returns mockk()

        // When
        val result = garPriceApi.getGasPrice()

        // Then
        assertNotNull(result)
        coVerify { gasPriceService.getLatest(eq("Bearer $fakeToken")) }
        coVerify { authenticationRepository.getAuthToken() }
    }

    @Test
    fun `Test getGasHistory() with failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getHistories(any()) } throws SocketException("Api error")

        // When
        val errorOccurred = try {
            garPriceApi.getGasHistory()
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
        coVerify { gasPriceService.getHistories(eq("Bearer $fakeToken")) }
        coVerify { authenticationRepository.getAuthToken() }
    }

    @Test
    fun `Test getGasHistory() with result`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { gasPriceService.getHistories(any()) } returns listOf()

        // When
        val result = garPriceApi.getGasHistory()

        // Then
        assertNotNull(result)
        coVerify { gasPriceService.getHistories(eq("Bearer $fakeToken")) }
        coVerify { authenticationRepository.getAuthToken() }
    }

}