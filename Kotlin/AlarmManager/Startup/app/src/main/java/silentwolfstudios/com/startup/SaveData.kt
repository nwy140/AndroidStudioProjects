package silentwolfstudios.com.startup

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SaveData{

    var context:Context?=null;
    var shareRef:SharedPreferences?=null

    constructor(context: Context){
        this.context=context;
        this.shareRef=context.getSharedPreferences("myref",Context.MODE_PRIVATE )
    }

    fun SaveData(hour:Int,minute: Int){
        var editor=shareRef!!.edit()
        editor.putInt("hours",hour)
        editor.putInt("minute",minute)
        editor.commit()
    }

    fun getHour():Int{
        return shareRef!!.getInt("hour",0)
    }
    fun getMinute():Int{
        return shareRef!!.getInt("minute",0)
    }


    fun setAlarm () {
        val hour:Int = getHour()
        val minute:Int=getMinute()

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