package silentwolfstudios.com.startup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class myBroadcastReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action.equals("com.tester.alarmmanager")){

            var b=intent.extras

            Toast.makeText(context,b.getString("message"),Toast.LENGTH_LONG).show()
            var loopnotifyme:Boolean=true;

val notifyme=Notifications();
            while (loopnotifyme==true){notifyme.Notify(context!!,b.getString("message"),10)}


        }else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")) { //system reset
            //TODO save time
            val saveData=SaveData(context!!) //bug001
            saveData.setAlarm()

        }
    } //register in manifest

}