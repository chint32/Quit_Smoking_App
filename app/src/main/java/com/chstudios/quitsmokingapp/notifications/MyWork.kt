package com.chstudios.quitsmokingapp.notifications

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    val mContext: Context

    init {
        mContext = context
    }

    override fun doWork(): Result {
        createNotification()
        return Result.success()
    }

    fun createNotification() {
        Log.d("My work", "creating notification")
        val notificationHelper = NotificationHelper(mContext)
        val nb =
            notificationHelper.channelNotification
        notificationHelper.manager!!.notify(1, nb.build())
    }
}