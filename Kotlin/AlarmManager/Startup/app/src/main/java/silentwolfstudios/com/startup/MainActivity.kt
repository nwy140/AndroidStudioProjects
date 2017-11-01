package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class   MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveData=SaveData(applicationContext) //bug001

        tvShowTime.text=saveData.getHour() .toString()+":"+ saveData.getMinute().toString()

    }

    fun SetTime(Hours:Int, Minute:Int){

        tvShowTime.text=Hours.toString()+":"+ Minute.toString()

        val saveData=SaveData(applicationContext) //bug001
        saveData.SaveData(Hours,Minute)
        saveData.setAlarm()
    }



    fun BuSetTime(view: View){
        val popTime=PopTime();
        val fm=fragmentManager
        popTime.show(fm,"Select time")
    }

}
