package silentwolfstudios.com.startup

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {
    var id=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        try{
            var bundle:Bundle=intent.extras; //read edit activity

            id=bundle.getInt("ID",0) //for update
            if(id!=0){
            etTitle.setText(bundle.getString("name").toString() )
            etDes.setText(bundle.getString("des")  )
            }
        }catch (ex:Exception){}
    }

    fun buAdd(view: View){
//        finish(); //finish activity
        var dbManager=DbManager(this)

        var  values=ContentValues();
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())

        if(id==0) { //if id==0 means user clicked home page without passing any information
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, " cannot add note", Toast.LENGTH_LONG).show();

            }
        }else{
            var selectionArgs= arrayOf(id.toString())

            val ID = dbManager.Update(values,"ID=?",selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, " cannot add note", Toast.LENGTH_LONG).show();

            }

        }

    finish()
    }
}
