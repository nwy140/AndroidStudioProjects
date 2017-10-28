package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_main.*



class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ivImagePerson.setOnClickListener(View.OnClickListener {
            //TODO SELECT IMAGE FROM PHONE
        })
    }
    fun buLogin (view: View){

    }
}
