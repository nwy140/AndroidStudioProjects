package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    protected fun buClick(view:View){
        val buSelected:Button = view as Button
        var cellID=0

        when (buSelected.id){
            R.id.bu -> cellID=1;
            R.id.bu2 -> cellID=2;
            R.id.bu3 -> cellID=3;
            R.id.bu4 -> cellID=4;
            R.id.bu5 -> cellID=5;
            R.id.bu6 -> cellID=6;
            R.id.bu7 -> cellID=7;
            R.id.bu8 -> cellID=8;
            R.id.bu9 -> cellID=9;

        }
        Toast.makeText(this,"ID:"+cellID, Toast.LENGTH_LONG);
    }



}
