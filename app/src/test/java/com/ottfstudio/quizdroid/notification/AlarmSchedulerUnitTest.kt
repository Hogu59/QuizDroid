package com.ottfstudio.quizdroid.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class AlarmSchedulerUnitTest {
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var mockPendingIntent: PendingIntent
    private lateinit var scheduler: AlarmScheduler

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic(PendingIntent::class)
        context = mockk(relaxed = true)
        alarmManager = mockk(relaxed = true)
        mockPendingIntent = mockk(relaxed = true)

        every { context.getSystemService(Context.ALARM_SERVICE) } returns alarmManager

        val intentSlot = slot<android.content.Intent>()
        every {
            PendingIntent.getBroadcast(
                context,
                AlarmScheduler.ALARM_REQUEST_CODE,
                capture(intentSlot),
                any(),
            )
        } returns mockPendingIntent

        // API ≥ S 권한 허용 흐름
        every { alarmManager.canScheduleExactAlarms() } returns true

        val fixedTime = Calendar.getInstance().apply {
            set(2025, Calendar.MAY, 1, 9, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        scheduler = AlarmScheduler(timeProvider = { fixedTime })
    }

    @Test
    fun `before 10am schedules exact alarm and returns true`() {
        val result = scheduler.scheduleDailyAlarm(context)
        assertTrue(result)

        verify {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                withArg { timeMillis ->
                    val calendar = Calendar.getInstance().apply {
                        timeInMillis = timeMillis
                    }
                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    val minute = calendar.get(Calendar.MINUTE)
                    assertTrue(hour == 10 && minute == 0)
                },
                mockPendingIntent,
            )
        }
    }

    @Test
    fun `after 10am schedules tomorrow exact alarm`() {
        val afterTime = Calendar.getInstance().apply {
            set(2025, Calendar.MAY, 1, 11, 30, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        val afterScheduler = AlarmScheduler(timeProvider = { afterTime })

        val result = afterScheduler.scheduleDailyAlarm(context)
        assertTrue(result)

        verify {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                withArg { timeMillis ->
                    val calendar = Calendar.getInstance().apply {
                        timeInMillis = timeMillis
                    }
                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    val minute = calendar.get(Calendar.MINUTE)
                    assertTrue(hour == 10 && minute == 0)
                },
                mockPendingIntent,
            )
        }
    }

    @Test
    fun `when exception occurs returns false`() {
        every {
            alarmManager.setExactAndAllowWhileIdle(
                any(),
                any(),
                any(),
            )
        } throws RuntimeException("테스트 예외")

        val result = scheduler.scheduleDailyAlarm(context)

        assertFalse(result)
    }
}
