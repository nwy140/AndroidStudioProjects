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

    fun buNumbers(view: View){ // Typing numbers
        if (isNewOp){
            etShowNumber.setText("" ) ;
        }
        isNewOp=false;
    //bug fixes
      var dotAdded = false;
    //


        val buSelect = view as  Button;
        var buClickValue:String=etShowNumber.text.toString();
        when (buSelect.id){
            bu0.id->{
                buClickValue+="0"
            }
            bu1.id->{
                buClickValue+="1"
            }
            bu2.id->{
                buClickValue+="2"
            }
            bu3.id->{
                buClickValue+="3"
            }
            bu4.id->{
                buClickValue+="4"
            }
            bu5.id->{
                buClickValue+="5"
            }
            bu6.id->{
                buClickValue+="6"
            }
            bu7.id->{
                buClickValue+="7"
            }
            bu8.id->{
                buClickValue+="8"
            }
            bu9.id->{
                buClickValue+="9"
            }
            buDot.id->{
                //TODO: prevent adding more than 1 dot
                buClickValue="."

            }
            buPlusMinus.id->{
                buClickValue="-" + buClickValue
            }
        }
        etShowNumber.setText(buClickValue);
    }


    var oldNumber="1"
    var op = "*";
    var isNewOp=true;

    fun buOpEvent(view: View){

        val buSelect = view as  Button;
        var buClickValue:String=etShowNumber.text.toString();
        when (buSelect.id){
            buMul.id->{
                op="*"
            }
            buDiv.id->{
                op="/"
            }
            buSum.id->{
                op="+"
            }
            buSub.id->{
                op="-"
            }

        }
        oldNumber=etShowNumber.text.toString();
//        etShowNumber.setText(oldNumber)//

        //Debug
//        buAC.setText(oldNumber.toString())
        //Debug
        isNewOp=true;

    }

    fun buEqualEvent (view: View){ //ERROR, old number can't be double ? WHY?????
        //stupid old number is still null? even after assigning value
        val newNumber=etShowNumber.text.toString();

        //Debug
//        var finalNumber:Double? = oldNumber.toDouble() ;

        //Debug
        var finalNumber:Double? = null ;
//        finalNumber=  oldNumber.toDouble()  ;

//        finalNumber=oldNumber.toDouble()+newNumber.toDouble()
        when (op){

            "*"->{
                finalNumber=(oldNumber.toDouble() *newNumber.toDouble())   ;
            }
            "/"->{
                finalNumber=oldNumber.toDouble() /newNumber.toDouble();
            }
            "+"->{
                finalNumber=oldNumber.toDouble() +newNumber.toDouble();
            }
            "-"->{
                finalNumber=oldNumber.toDouble() -newNumber.toDouble();
            }
        }

          etShowNumber.setText(finalNumber.toString())  ;
//        etShowNumber.setText(oldNumber.toString());
        isNewOp=true;
        //Error Hussein
        //Calculation Error?

    }

    fun buPercent (view: View){

        val number=etShowNumber.text.toString().toDouble()/100;

        etShowNumber.setText(number.toString())
        isNewOp=true;

    }

    fun buClean (view: View){
        etShowNumber.setText("0");
        isNewOp=true;
    }



}
