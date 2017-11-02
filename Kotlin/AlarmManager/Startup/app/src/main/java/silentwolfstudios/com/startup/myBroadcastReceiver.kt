package silentwolfstudios.com.startup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast

class myBroadcastReceiver:BroadcastReceiver(){

    var mp:MediaPlayer?=null;
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action.equals("com.tester.alarmmanager")){

            var b=intent.extras

            Toast.makeText(context,b.getString("message"),Toast.LENGTH_LONG).show()
            mp= MediaPlayer()
            try{
                Toast.makeText(context,"Play Alarm",Toast.LENGTH_LONG).show()
                mp!!.setDataSource("https://vocaroo.com/media_command.php?media=s0wUTLS8DBtU&command=download_mp3")
                mp!!.prepare()
                mp!!.start()
                Toast.makeText(context,"Alarm started",Toast.LENGTH_LONG).show()

                mp!!.setOnCompletionListener {while (true){ mp!!.start() } }

            }catch (ex:Exception){
                Toast.makeText(context,"Alarm failed to started, you need internet to access alarm's audio",Toast.LENGTH_LONG).show()
0
            }



            val notifyme=Notifications();
            notifyme.Notify(context!!,b.getString("message"),10)




        }else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")) { //system reset
            //TODO save time
            val saveData=SaveData(context!!) //bug001
            saveData.setAlarm()

        }
    } //register in manifest

}