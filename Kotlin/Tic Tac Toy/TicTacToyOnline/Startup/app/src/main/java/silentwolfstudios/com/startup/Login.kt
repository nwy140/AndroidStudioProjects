package silentwolfstudios.com.startup

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import silentwolfstudios.com.startup.R

class Login : AppCompatActivity() {

    private var mAuth:FirebaseAuth?=null; //FireBase authentication

    private var database=FirebaseDatabase.getInstance();
    private var myRef=database.reference;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth= FirebaseAuth.getInstance();
    }

    fun buLoginEvent(view:View){
        LoginToFireaBase(etEmail.text.toString(),etPassword.text.toString())
    }

    fun LoginToFireaBase(email:String,password:String) {

    mAuth!!.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->

                if (task.isSuccessful){
                    Toast.makeText(applicationContext,"Successful Login",Toast.LENGTH_LONG).show();

                    var currentUser =mAuth!!.currentUser;

                    //save in database
                    if(currentUser!=null){
//                        myRef.child("Users").child(SplitString(currentUser.email.toString())).setValue(currentUser.uid) // you can't load email as name because Firebase can't load '@' char in their database
                        myRef.child("Users").child(SplitString(currentUser.email.toString())).child("Request").setValue(currentUser.uid) // you can't load email as name because Firebase can't load '@' char in their database

                    }

                    LoadMain()
                }else {
                    Toast.makeText(applicationContext,"Fail  Login",Toast.LENGTH_LONG).show();

                }

            }
    }

    override fun onStart() {
        super.onStart()
        LoadMain();
    }

    fun LoadMain(){
        var currentUser =mAuth!!.currentUser;

        if(currentUser!=null){



            var intent=Intent(this, MainActivity::class.java);
            intent.putExtra("email",currentUser.email);
            intent.putExtra("uid",currentUser.uid);
            startActivity(intent)
        }

    }

    fun SplitString(str:String):String{
        var split=str.split("@")
        return split[0]
    }



}
