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


        }
    } //register in manifest

}