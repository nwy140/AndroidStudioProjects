package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        buFindAge.setOnClickListener {
            var yearOfBirth = txtYearofBirth.text.toString().toInt();
            // they don't have direct method to convert to int , so you need to convert to string first, then to int
            val currentYear = Calendar.getInstance().get(Calendar.YEAR);
            var myAge = currentYear-yearOfBirth;
            tvShowAge.text = "Your are $myAge years old";

            }
        */

        // fire when button is clicked

//        tvDisplay.text="Welcome to Kotlin";


    }
    fun buFindAgeEvent(view:View){
        var yearOfBirth:Int = txtYearofBirth.text.toString().toInt();
        val currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (yearOfBirth==0 || yearOfBirth>currentYear){
            tvShowAge.text="invalid input";
            return;
        }
        // they don't have direct method to convert to int , so you need to convert to string first, then to int
        var myAge = currentYear-yearOfBirth.toInt();
        val avg = myAge/yearOfBirth

        tvShowAge.text = "Your are " + myAge.toString() + " years old";

    }

}