package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_main.*
import silentwolfstudios.com.startup.R
import java.util.*

class MainActivity : AppCompatActivity() {

    //database instance
    private var database= FirebaseDatabase.getInstance();
    private var myRef=database.reference;

    var myEmail:String?=null;
private var mFirebaseAnalytics:FirebaseAnalytics?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        var b:Bundle=intent.extras;
        myEmail=b.getString("email") // will get email, then send it to extras, and set the email to login email
                                            // so whenever you click request, you will add your email to database
    }
var winner=-1;
     fun buClick(view: View) {  // do not use protected ,it causes crash
        var buSelected = view as Button;
        var cellID = 0;

//stop gameplay
         if (winner==1 || winner==2){player1.clear(); player2.clear();return;}
//stop gameplay



        when (buSelected.id) {
            R.id.bu1 -> cellID = 1;
            R.id.bu2 -> cellID = 2;
            R.id.bu3 -> cellID = 3;
            R.id.bu4 -> cellID = 4;
            R.id.bu5 -> cellID = 5;
            R.id.bu6 -> cellID = 6;
            R.id.bu7 -> cellID = 7;
            R.id.bu8 -> cellID = 8;
            R.id.bu9 -> cellID = 9;


        }
//        Toast.makeText(this, "ID:" + cellID, Toast.LENGTH_LONG).show()

        PlayGame(cellID,buSelected);
    }

    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    var ActivePlayer=1

    fun PlayGame(cellID:Int,buSelected:Button){
        if (ActivePlayer==1){
            buSelected.text="X";
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            ActivePlayer=2
            AutoPlay()
        } else {
            buSelected.text="O";
            buSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellID)
            ActivePlayer=1
        }


        buSelected.isEnabled=false;
        CheckWinner();
    }

    fun CheckWinner(){
//        var winner=-1;

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner=1;
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner=2;
        }

        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner=1;
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner=2;
        }
        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner=1;
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner=2;
        }

        // slashes 01
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner=1;
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner=2;
        }
        // slashes 02
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner=1;
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner=2;
        }
        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner=1;
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner=2;
        }
        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner=1;
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner=2;
        }
        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner=1;
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner=2;
        }

        if (winner!=-1){

            if(winner==1 && winner!=2){
                Toast.makeText(this, "Player 1 win the game" , Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Player 2 win the game" , Toast.LENGTH_LONG).show()
            }

        }




    }

    fun AutoPlay(){
        var emptyCell=ArrayList<Int>()
        for ( cellID in 1..9  ){
            if (!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCell.add(cellID);
            }
        }
        var r=Random();

        val randIndex=r.nextInt(emptyCell.size-0)+0;

        val cellID= emptyCell.get(randIndex);
        var buSelect:Button?
        when (cellID){
            1-> buSelect=bu1;
            2-> buSelect=bu2;
            3-> buSelect=bu3;
            4-> buSelect=bu4;
            5-> buSelect=bu5;
            6-> buSelect=bu6;
            7-> buSelect=bu7;
            8-> buSelect=bu8;
            9-> buSelect=bu9;
            else->{
                buSelect=bu1
            }
        }
        PlayGame(cellID,buSelect);
    }

    fun buRequestEvent(view: android.view.View){
        var userDemail=etEmail.text.toString();
        myRef.child("Users").child(userDemail).child("Request").push().setValue(myEmail) //push means create node with random ID

    }

    fun buAcceptEvent(view: android.view.View){
        var userDemail=etEmail.text.toString();


    }


}
