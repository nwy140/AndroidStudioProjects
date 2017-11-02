package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_ticket.view.*

class MainActivity : AppCompatActivity() {


    var ListSongs=ArrayList<SongInfo>()
    var adapter:MySongAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoadURLOnline()
        adapter=MySongAdapter(ListSongs)
        lsListSongs.adapter=adapter
    }

    fun LoadURLOnline(){
        ListSongs.add(SongInfo("Critical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
        ListSongs.add(SongInfo("01 Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
        ListSongs.add(SongInfo("02 Critical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
        ListSongs.add(SongInfo("03  C  ritical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
        ListSongs.add(SongInfo("04 Critical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
    }

    inner class MySongAdapter:BaseAdapter{

        var myListSong=ArrayList<SongInfo>()
        constructor(myListSong:ArrayList<SongInfo>):super(){
            this.myListSong=myListSong

        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView=layoutInflater.inflate(R.layout.song_ticket,null)
            val Song=this.myListSong[position]
            myView.tvSongName.text = Song.Title
            myView.tvAuthor.text = Song.AuthorName

            myView.buPlay.setOnClickListener(View.OnClickListener {
                //TODO: play Song
            })
            return  myView

        }

        override fun getItem(position: Int): Any {
            return this.myListSong[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return this.myListSong.size
        }

    }

}
