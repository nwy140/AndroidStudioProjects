package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class   MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun SetTime(Hours:Int, Minute:Int){

        tvShowTime.text=Hours.toString()+":"+ Minute.toString()

        val saveData=SaveData(applicationContext) //bug001
        saveData.setAlarm(Hours, Minute)
    }


    fun BuSetTime(view: View){
        val popTime=PopTime();
        val fm=fragmentManager
        popTime.show(fm,"Select time")
    }

}
