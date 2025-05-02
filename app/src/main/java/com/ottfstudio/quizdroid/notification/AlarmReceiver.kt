package com.ottfstudio.quizdroid.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationWorkRequest =
            OneTimeWorkRequestBuilder<DailyProblemNotificationWorker>().build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "daily_problem_notification",
                ExistingWorkPolicy.REPLACE,
                notificationWorkRequest,
            )

        if (intent.getBooleanExtra(AlarmScheduler.EXTRA_RESCHEDULE, false)) {
            createAlarmScheduler().scheduleDailyAlarm(context)
        }
    }

    private fun createAlarmScheduler(): AlarmScheduler {
        return AlarmScheduler()
    }
}
