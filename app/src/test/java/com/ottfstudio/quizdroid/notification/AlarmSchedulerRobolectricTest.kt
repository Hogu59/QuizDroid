package com.ottfstudio.quizdroid.notification

import android.app.AlarmManager
import android.content.Context
import android.os.SystemClock
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.util.Calendar

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class AlarmSchedulerRobolectricTest {
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    @Test
    fun `before 10am schedules today at 10am`() {
        val calendar = Calendar.getInstance().apply {
            set(2025, Calendar.MAY, 1, 9, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }
        SystemClock.setCurrentTimeMillis(calendar.timeInMillis)

        val fixedTime = calendar.timeInMillis
        val scheduler = AlarmScheduler(timeProvider = { fixedTime })

        scheduler.scheduleDailyAlarm(context)

        val shadow = shadowOf(alarmManager)
        val alarms = shadow.scheduledAlarms
        assertEquals(1, alarms.size)

        val calendarExpect = Calendar.getInstance().apply {
            timeInMillis = calendar.timeInMillis
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        assertEquals(calendarExpect.timeInMillis, alarms[0].triggerAtMs)
    }

    @Test
    fun `after 10am schedules tomorrow at 10am`() {
        val calendar = Calendar.getInstance().apply {
            set(2025, Calendar.MAY, 1, 11, 30, 0)
            set(Calendar.MILLISECOND, 0)
        }
        SystemClock.setCurrentTimeMillis(calendar.timeInMillis)

        val fixedTime = calendar.timeInMillis
        val scheduler = AlarmScheduler(timeProvider = { fixedTime })

        val shadow = shadowOf(alarmManager)
        val alarmsBefore = shadow.scheduledAlarms
        assertEquals(0, alarmsBefore.size)

        scheduler.scheduleDailyAlarm(context)

        val alarms = shadow.scheduledAlarms
        assertEquals(1, alarms.size)

        val calExp = Calendar.getInstance().apply {
            timeInMillis = calendar.timeInMillis
            add(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        assertEquals(calExp.timeInMillis, alarms[0].triggerAtMs)
    }
}
