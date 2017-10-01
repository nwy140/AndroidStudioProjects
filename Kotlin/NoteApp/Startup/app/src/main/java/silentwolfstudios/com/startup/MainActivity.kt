package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {


    var listNotes=ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Add dummy data
        listNotes.add(Note(1,"Meet Professor", "Cristina, Houin kyouma , el psy congroo  ,IBM 5100,dnawjdbdwawdnawjdbwajdbawhdbwahjbadwhbdawhbdwahbdawhbdaw"))
        listNotes.add(Note(2,"Meet doctor", "Cristina, Houin kyouma , el psy kongroo  ,IBM 5100,dnawjdbdwawdnawjdbwajdbawhdbwahjbadwhbdawhbdwahbdawhbdaw"))
        listNotes.add(Note(3,"Meet friend", "Cristina, Houin kyouma , el psy kongroo  , IBM 5100,dnawjdbdwawdnawjdbwajdbawhdbwahjbadwhbdawhbdwahbdawhbdaw"))


        //adapter
        var myNotesAdapter=  MyNotesAdapter(listNotes);
        lvNote.adapter = myNotesAdapter
    }


    //BaseAdapter used to pass data
    inner class MyNotesAdapter:BaseAdapter{
        constructor(listNotesAdapter:ArrayList<Note>):super(){
            this.listNotesAdapter=listNotesAdapter;

        }

        //getView method called getCount() times
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var myView=layoutInflater.inflate(R.layout.ticket,null);
            var myNode=listNotesAdapter[p0]
            myView.tvTitle.text = myNode.nodeName;
            myView.tvDes.text = myNode.nodeDes;

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



}
