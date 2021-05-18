package dev.marcocattaneo.push.data.interactors

import dev.marcocattaneo.gasprice.common.models.FirestoreDate
import dev.marcocattaneo.push.data.CoroutineTestRule
import dev.marcocattaneo.push.data.mapper.AlarmMapper
import dev.marcocattaneo.push.data.models.Alarm
import dev.marcocattaneo.push.data.repositories.AlarmsRepository
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

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetAlarmsUseCaseTest {

    @MockK
    lateinit var alarmsRepository: AlarmsRepository

    lateinit var getAlarmsUseCase: GetAlarmsUseCase

    @get:Rule
    private val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getAlarmsUseCase = GetAlarmsUseCase(alarmsRepository, AlarmMapper())
    }

    @Test
    fun `Test get alarms with an api success`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { alarmsRepository.getAll() } returns listOf(
            Alarm(
                "id", "uuid", 12.0, true,
                FirestoreDate(0L, 0L),
                FirestoreDate(0L, 0L)
            )
        )

        // When
        val result = getAlarmsUseCase.execute(null)

        // Then
        assert(result.isNotEmpty())
        assertEquals("id", result.first().id)
        assertEquals(12.0, result.first().limit, 0.0)
        assertEquals(true, result.first().enabled)
    }

    @Test
    fun `Test get alarms with an api failure`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Given
        coEvery { alarmsRepository.getAll() } throws IllegalStateException("API error")

        // When
        val errorOccurred = try {
            getAlarmsUseCase.execute(null)
            null
        } catch (e: Exception) {
            e
        }

        // Then
        assertNotNull(errorOccurred)
    }

}