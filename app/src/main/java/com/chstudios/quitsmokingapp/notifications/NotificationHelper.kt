package com.chstudios.quitsmokingapp.notifications

import android.R
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.chstudios.quitsmokingapp.MainActivity

class NotificationHelper(base: Context?) : ContextWrapper(base) {
    private var mManager: NotificationManager? = null
    lateinit var resultPendingIntent: PendingIntent
    var motivationalQuotes =
        mutableListOf<String>()

    companion object {
        const val channelID = "channelID"
        const val channelName = "Channel Name"
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        fillQuotesList(motivationalQuotes)
    }

    val manager: NotificationManager?
        get() {
            if (mManager == null) {
                mManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return mManager
        }

    val channelNotification: NotificationCompat.Builder
        get() = NotificationCompat.Builder(applicationContext, channelID)
            .setContentText(motivationalQuotes.random())
            .setSmallIcon(R.drawable.ic_input_add)
            .setContentIntent(resultPendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel = NotificationChannel(
            channelID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        manager!!.createNotificationChannel(channel)
        val resultIntent = Intent(this, MainActivity::class.java)
        // Create the TaskStackBuilder
        resultPendingIntent = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }!!
    }

    private fun fillQuotesList(motivationalQuotes: MutableList<String>){
        motivationalQuotes.add("The threat of nicotine withdrawal is like having a cat on your doorstep but in your mind, you interpret the cat as a tiger.")
        motivationalQuotes.add("Quitting smoking has nothing to do with nicotine withdrawal but everything to do with our minds.")
        motivationalQuotes.add("In terms of physical pain, the withdrawal from nicotine rates nowhere; there is no physical pain at all.")
        motivationalQuotes.add("Nicotine withdrawal is extremely mild. It is just a slightly empty feeling or a feeling that something isn’t quite right.")
        motivationalQuotes.add("Nicotine or the use of tobacco products would have been banned a long long time ago if there are serious withdrawal effects.")
        motivationalQuotes.add("Just because withdrawal is mild does not mean nicotine is not dangerous. Nicotine makes a formidable enemy because of its subtlety.")
        motivationalQuotes.add("Nicotine is known as the silent killer. Don't put it in your body.")
        motivationalQuotes.add("Nicotine is extremely fasting-acting. Just one cigarette is enough to get an adult hooked and one puff to get an ex-smoker hooked again.")
        motivationalQuotes.add("It only takes 3 days for nicotine to be expelled from the body. After that, it's just about breaking the habit.")
        motivationalQuotes.add("People say ‘give up’ smoking but the truth is there’s nothing to give up. We are not giving up anything when we stop smoking.")
        motivationalQuotes.add("Nicotine has no strength on its own; its power derives entirely from our fear of it.")
        motivationalQuotes.add("Cigarettes are void creators, not void fillers")
        motivationalQuotes.add("As long as we think smoking gives us ‘pleasure,’ we will NEVER be a happy non-smoker.")
        motivationalQuotes.add("The biggest lie of smoking is believing it gives us an intrinsic pleasure.")
    }
}