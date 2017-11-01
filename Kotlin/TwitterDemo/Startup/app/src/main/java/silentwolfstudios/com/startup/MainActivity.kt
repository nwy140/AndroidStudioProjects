package silentwolfstudios.com.startup

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_ticket.view.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var database= FirebaseDatabase.getInstance();
    private var myRef=database.reference


    var ListTweets=ArrayList<Ticket>()
    var adapter:MyTweetAdapter?=null;
    var b:Bundle=intent.extras
    var myemail:String=b.getString("email")
    var UserUID:String=b.getString("uid")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//defining bundle to access variables from previous activity email and UID
//        var b:Bundle=intent.extras
//        myemail=b.getString("email").toString();
//        UserUID=b.getString("uid")

        //Dummy data
        ListTweets.add(Ticket("0","him","url","add"))
        ListTweets.add(Ticket("0","him","url","hussein"))
        ListTweets.add(Ticket("0","him","url","hussein"))
        ListTweets.add(Ticket("0","him","url","hussein"))


        adapter=MyTweetAdapter(this,ListTweets)
        lvTweets.adapter=adapter

    }
    inner class MyTweetAdapter:BaseAdapter{
        //        var listTweetAdapter=ArrayList<Tweet>()
        var context:Context?=null;
        constructor(context:Context,listTweetAdapter:ArrayList<Ticket>):super(){
            this.listTweetAdapter=listTweetAdapter;
            this.context=context;
        }

        //getView method called getCount() times
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var myTweet  =listTweetAdapter[p0]
//        var layoutInflater:LayoutInflater //testing to avoid buggy android studio not detecting layout inflater
            if(myTweet.tweetPersonUID.equals("add")){
                var myView=layoutInflater.inflate(R.layout.add_ticket,null);
                //Load add ticket

                myView.iv_attach.setOnClickListener { View.OnClickListener{
                    loadImage() //when loadimage calleed, it will load intent, then upload it to firebase
                } }

                myView.iv_post.setOnClickListener (View.OnClickListener {
                    //Upload to server
                    myRef.child("posts").push().child("UserUID").setValue(UserUID) //push gives random ID to firebase
                    myRef.child("posts").push().child("text").setValue(myView.etPost.text.toString()) //push gives random ID to firebase
                    myRef.child("posts").push().child("postImage").setValue(DownloadURL) //push gives random ID to firebase
                })
                return  myView
            }else{
                var myView=layoutInflater.inflate(R.layout.tweets_ticket,null);
                //Load tweet ticket
                //TODO: work
                return  myView

            }



        }

        override fun getItem(p0: Int): Any {
            return listTweetAdapter[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong();
        }



        override fun getCount(): Int {
            return listTweetAdapter.size;
        }

        var listTweetAdapter=ArrayList<Ticket>()


    }
    //Load image
    val PICK_IMAGE_CODE=123
    fun loadImage(){
        //TODO Load Image
        var intent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMAGE_CODE ) //Activity expects result, OnAcitivtyResults will be called after activity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_IMAGE_CODE && data!=null && resultCode== RESULT_OK){
            val selectedImage=data.data
            val filePathColum= arrayOf(MediaStore.Images.Media.DATA)
            val cursor= contentResolver.query(selectedImage,filePathColum,null,null,null)
            cursor.moveToFirst()
            val columIndex=cursor.getColumnIndex(filePathColum[0])
            val picturePATH=cursor.getString(columIndex);
            cursor.close()
            UploadImage(BitmapFactory.decodeFile(picturePATH))



        }
    } //Request Permission and Select Image from Phone

    var DownloadURL:String?=null;
    fun UploadImage(bitmap:Bitmap){
        val storage= FirebaseStorage.getInstance()
        val storageRef=storage.getReferenceFromUrl("gs://tictactoeudemy-7e3fb.appspot.com")
        val df= SimpleDateFormat("ddMMyyHHmmss")
        val dataobj= Date();
        val imagePATH=SplitString(myemail).toString() + "." +df.format(dataobj)+".jpg"
        val ImageRef=storageRef.child("imagePost/" + imagePATH);
        ivImagePerson.isDrawingCacheEnabled=true;
        ivImagePerson.buildDrawingCache()


        val baos= ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        val data=baos.toByteArray()
        val uploadTask=ImageRef.putBytes(data);
        uploadTask.addOnFailureListener{
            Toast.makeText(applicationContext,"Fail to Upload", Toast.LENGTH_LONG).show();

        }.addOnSuccessListener {taskSnapshot ->
            DownloadURL=taskSnapshot.downloadUrl!!.toString()

        }
    }
    fun SplitString(email: String): String {
        val  split=email.split("@")
        return split[0];
    }
}
