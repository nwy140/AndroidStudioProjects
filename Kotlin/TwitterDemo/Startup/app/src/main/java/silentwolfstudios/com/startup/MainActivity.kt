package silentwolfstudios.com.startup

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var ListTweets=ArrayList<Ticket>()
    var adapter:MyTweetAdapter?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
                //TODO: work
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
}
