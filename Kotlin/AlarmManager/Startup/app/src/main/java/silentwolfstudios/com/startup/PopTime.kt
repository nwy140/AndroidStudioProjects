package silentwolfstudios.com.startup

import android.app.DialogFragment
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import kotlinx.android.synthetic.main.pop_time.*

class PopTime:DialogFragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var myView=inflater!!.inflate(R.layout.pop_time,container,false)

        var buDone=myView.findViewById<Button>(R.id.buDone) as Button

        var tp1=myView.findViewById<TimePicker>(R.id.tp1) as TimePicker

        buDone.setOnClickListener(View.OnClickListener {
            //Code here
            val ma=activity as MainActivity //if you want to access another activity, you can declare it as activity
            if (Build.VERSION.SDK_INT>=23) {
                ma.SetTime(tp1.hour, tp1.minute)
            }else{
                ma.SetTime(tp1.currentHour, tp1.currentMinute)
            }

            this.dismiss()
        })

        return myView
    }
}