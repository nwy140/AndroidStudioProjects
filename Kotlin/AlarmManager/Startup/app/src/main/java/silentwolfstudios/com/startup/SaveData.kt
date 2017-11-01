package silentwolfstudios.com.startup

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class SaveData{

    var context:Context?=null;
    constructor(context: Context){
        this.context=context;
    }

    fun setAlarm (hour:Int,minute:Int) {

        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY, hour)
        calender.set(Calendar.MINUTE, minute)
        calender.set(Calendar.SECOND,0)
        //if day is not set, default is this day

        val am=context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
var intent=Intent(context,myBroadcastReceiver::class.java   )
        intent.putExtra("message","alarm time")
        intent.action="com.tester.alarmmanager" //send action which is equal to the one in manifest to be received by receiver to activtate broadcast receiver method
        val pi=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT    )

        am.setRepeating(AlarmManager.RTC_WAKEUP,calender.timeInMillis,AlarmManager.INTERVAL_DAY,pi)

    }


}