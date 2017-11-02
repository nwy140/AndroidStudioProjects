package silentwolfstudios.com.startup

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_ticket.view.*

class MainActivity : AppCompatActivity() {


    var ListSongs=ArrayList<SongInfo>()
    var adapter:MySongAdapter?=null
    var mp:MediaPlayer?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoadURLOnline()
        CheckUserPermsions()
//        adapter=MySongAdapter(ListSongs)
//        lsListSongs.adapter=adapter

        var mytracking=mySongTrack()
        mytracking.start()
    }

    fun LoadURLOnline(){

        ListSongs.add(SongInfo("Cycle","SteinsGate","http://66.90.93.122/ost/steins-gate-symphonic-reunion/vrioowfnnd/02-cycle.mp3"))
        ListSongs.add(SongInfo("Laboratory","SteinsGate","http://66.90.93.122/ost/steins-gate-symphonic-reunion/dmjgwkqgtu/06-laboratory.mp3"))
        ListSongs.add(SongInfo("Queit Air","SteinsGate","http://67.159.62.2/anime_ost/steins-gate-original-soundtrack/tejckcvbys/109%20Quiet%20air.mp3"))
        ListSongs.add(SongInfo("Human Communities","SteinsGate","http://67.159.62.2/anime_ost/steins-gate-original-soundtrack/qfhhzxpzdp/203%20Human%20community.mp3"))
        ListSongs.add(SongInfo("Select Of Sorrow","SteinsGate","http://67.159.62.2/anime_ost/steins-gate-original-soundtrack/zdtjuievdq/117%20Select%20of%20sorrow.mp3"))
        ListSongs.add(SongInfo("Gate Of Steiner","SteinsGate","http://67.159.62.2/anime_ost/steins-gate-original-soundtrack/viwbnxdawh/103%20GATE%20OF%20STEINER%20-Main%20theme-.mp3"))

        ListSongs.add(SongInfo("Critical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
//        ListSongs.add(SongInfo("01 Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
//        ListSongs.add(SongInfo("02 Critical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
//        ListSongs.add(SongInfo("03  C  ritical Drive","YokoShimomura","https://vocaroo.com/media_command.php?media=s1Cz7WMgL76d&command=download_mp3"))
        ListSongs.add(SongInfo("Explosion","SoundBible","http://soundbible.com/grab.php?id=2140&type=mp3"))
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
            if(myView.buPlay.text=="STOP"){
                mp!!.stop()
                myView.buPlay.text="START"

            }else{
                mp=MediaPlayer()
                try{
                    mp!!.setDataSource(Song.SongURL   )
                    mp!!.prepare()
                    mp!!.start()
                    myView.buPlay.text="STOP"
                    sbProgress.max= mp!!.duration
                    //restart music
                    mp!!.setOnCompletionListener { mp!!.start() }

                }catch (ex:Exception){}
            }

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
    inner class mySongTrack():Thread(){

        override fun run() {
            while (true) {
                try {
                    Thread.sleep(1000)
                } catch (ex: Exception) {
                }

                runOnUiThread {
                    if (mp != null) {
                        sbProgress.progress = mp!!.currentPosition
                    }

                }
            }
        }
    }
    //---loadaudiofromdevice
    fun CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_CODE_ASK_PERMISSIONS)
                return
            }
        }

        LoadSong()

    }

    //get acces to location permsion
    private val REQUEST_CODE_ASK_PERMISSIONS = 123


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LoadSong()
            } else {
                // Permission Denied
                Toast.makeText(this, "denail", Toast.LENGTH_SHORT)
                        .show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun   LoadSong() {
        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val cursor = contentResolver.query(allSongsURI, null, selection, null, null)
        if (cursor != null) {
            if (cursor!!.moveToFirst()) {

                do {

                    val songURL = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val SongAuthor = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val SongName = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    ListSongs.add(SongInfo(SongName, SongAuthor, songURL))
                } while (cursor!!.moveToNext())


            }
            cursor!!.close()

            adapter=MySongAdapter(ListSongs)
            lsListSongs.adapter=adapter
        }
    }
    //---loadaudiofromdevice---

}
