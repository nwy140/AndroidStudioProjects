package silentwolfstudios.com.startup

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.*
import kotlinx.android.synthetic.main.ticket.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    var listNotes=ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Add dummy data
//        listNotes.add(Note(1,"Meet Professor", "Cristina, Houin kyouma , el psy congroo  ,IBM 5100,dnawjdbdwawdnawjdbwajdbawhdbwahjbadwhbdawhbdwahbdawhbdaw"))
//        listNotes.add(Note(2,"Meet doctor", "Cristina, Houin kyouma , el psy kongroo  ,IBM 5100,dnawjdbdwawdnawjdbwajdbawhdbwahjbadwhbdawhbdwahbdawhbdaw"))
//        listNotes.add(Note(3,"Meet friend", "Cristina, Houin kyouma , el psy kongroo  , IBM 5100,dnawjdbdwawdnawjdbwajdbawhdbwahjbadwhbdawhbdwahbdawhbdaw"))


        //adapter
//        var myNotesAdapter=  MyNotesAdapter(listNotes);
//        lvNote.adapter = myNotesAdapter

        //Load from DbManager
        LoadQuery("%");


        //---SHOWING ACTIVITY LIFE CYCLE
    Toast.makeText(this,"OnCreate",Toast.LENGTH_LONG).show();
    }



    //--Create on resume
    override fun onResume() { //onResume means when the form comes back again, to the previous layout/activity or when activity finishes I think
        super.onResume()
        LoadQuery("%")
        Toast.makeText(this,"OnResume",Toast.LENGTH_LONG).show();
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"OnStart",Toast.LENGTH_LONG).show();
    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(this,"OnPause",Toast.LENGTH_LONG).show();
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"OnDestroy",Toast.LENGTH_LONG).show();
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show();
    }
    //--Create on resume---
    //---SHOWING ACTIVITY LIFE CYCLE---


    fun LoadQuery(title:String){
        var dbManager=DbManager(this)
        val projections= arrayOf("ID","Title","Description") //define colum you want to get instead  of null
        val selectionArgs= arrayOf("%") //% means return anything passed in function
        val cursor=dbManager.Query(projections,"Title like ?",selectionArgs,"Title"); //send null in projection to use all the column
        // eg if hussein search , search for TItle like hussein passed in parameter
        listNotes.clear();
        if(cursor.moveToFirst()){
        //if you have data , please move
            do {
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Description=cursor.getString(cursor.getColumnIndex("Description"))

            listNotes.add(Note(ID,Title,Description))
            }while (cursor.moveToNext())

            //send to adapter, to show changes to layout
            var myNotesAdapter=  MyNotesAdapter(this,  listNotes);
            lvNote.adapter = myNotesAdapter
        }
    }

    //when menu is created,  take menu from main_menu.xml
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.main_menu,menu);

        val sv=menu!!.findItem(R.id.app_bar_search).actionView as SearchView;
        val sm=getSystemService(Context.SEARCH_SERVICE) as SearchManager;
        sv.setSearchableInfo(sm.getSearchableInfo(componentName));
        sv.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query:String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show();
                LoadQuery("%"+query+"%");//Done:search database
                return false;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false;
            }
        //android:actionviewclass = widget  // changed to app:actionviewclass=widget

        } )//209329

        return super.onCreateOptionsMenu(menu)
    }

    // when menu is selected
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item!=null) {
        when (item.itemId) {
            R.id.addNote -> {
                //Go to Add page // To change activity, define your intent , then start Activity
                var intent=Intent(this,AddNotes::class.java);
                startActivity(intent);


            }
        }
    }
        return super.onOptionsItemSelected(item)
    }

    //BaseAdapter used to pass data
    inner class MyNotesAdapter:BaseAdapter{
//        var listNotesAdapter=ArrayList<Note>()
        var context:Context?=null;
        constructor(context:Context,listNotesAdapter:ArrayList<Note>):super(){
            this.listNotesAdapter=listNotesAdapter;
            this.context=context;
        }

        //getView method called getCount() times
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var myView=layoutInflater.inflate(R.layout.ticket,null);
            var myNote  =listNotesAdapter[p0]
            myView.tvTitle.text = myNote.nodeName;
            myView.tvDes.text = myNote.nodeDes;
            myView.ivDelete.setOnClickListener(View.OnClickListener {
                var dbManager=DbManager(this.context!!)

                val selectionArgs= arrayOf(myNote.nodeID.toString()) //% means return anything passed in function
                dbManager.Delete("ID=?",selectionArgs)
                    LoadQuery("%"); //Reload s
            })

            myView.ivEdit.setOnClickListener(View.OnClickListener {
                GoToUpdate(myNote)
            })
            return myView; // give output for calling later by adapter

        }

        override fun getItem(p0: Int): Any {
        return listNotesAdapter[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong();
        }



        override fun getCount(): Int {
            return listNotesAdapter.size;
        }

        var listNotesAdapter=ArrayList<Note>()


    }

    fun GoToUpdate(note:Note){
        //define edit intent and then start activity to start editing
        var intent=Intent(this,AddNotes::class.java);
        intent.putExtra("ID",note.nodeID)
        intent.putExtra("name",note.nodeName)
        intent.putExtra("des",note.nodeDes)

        startActivity(intent);
    }

}
