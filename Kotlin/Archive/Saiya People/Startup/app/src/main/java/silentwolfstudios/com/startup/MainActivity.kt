package silentwolfstudios.com.startup

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listofAnimals = ArrayList<Animal>();
    var adapter:AnimalsAdapter? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listofAnimals.add(Animal("Kakarotto","\nRemeber this saiyaman was born on earth. \n Its called movement movement.\n You can too?\nHere I cum Kuralla!" , R.drawable.kakarotto,true))
        listofAnimals.add(Animal("Bejita","Kakarotto is mine, nobody touches him, not even yu!!! Eawa er aaarrrrrgh\n Dont take saiya people likely. Do not misunderstand me kakarotto , I did not cum to hap you, da man who bit you is me" , R.drawable.bejita,true))
        listofAnimals.add(Animal("Kuralla"," I no have a broter\nAt Last you showed your real shit saiya people" , R.drawable.kuralla,true))
        listofAnimals.add(Animal("Turas","Kakarotto, you must come back to the saiya people. You have inherited the blood of saiya people AH, I am the saiya people. Saiya People? Yas" , R.drawable.turas,true))
        listofAnimals.add(Animal("Surag","Youth powa, you disturb pee.   \n Wat is dat sound." , R.drawable.surag,true))
        listofAnimals.add(Animal("Gay King","I see , black people dislike the sound of rubbing glass, wich is da sound of the whistle, probably the sound wave of the whistle, which a human being blows might echoes summly irritating the ears of the namek people,the nerve cut any other phase." , R.drawable.gayking,false))

        listofAnimals.add(Animal("Paragasu","By tomorow we will surely discover the super legend saiyas " , R.drawable.paragasu,false))

        listofAnimals.add(Animal("Goku","Uncle Motong, Uncle beggar." , R.drawable.goku,true))

        listofAnimals.add(Animal("Infinite Hands","If Goku is with us, it will be finish so easy, let us all enjoy da space travo" , R.drawable.infinitehands,false))
        listofAnimals.add(Animal("Uncle Motong","Your mind has been damagedly corrupted get loss. Sun, \ngive him the powa of da sun\n blow da whistle , R.drawable.white_tiger,true",R.drawable.unclemotong,isKiller = true))
        listofAnimals.add(Animal("Torankasu","You scrap metal hulk, for your information I am from 20 years in the future, and in that future you were not around, and is that I know all, that is because Mr Goku is going to destroy you today!!" , R.drawable.torankasu,true))

        listofAnimals.add(Animal("Oolong","What kinda star kiss" , R.drawable.oolong,false))
        listofAnimals.add(Animal("Bardock","Was dat" , R.drawable.bardock,true))




        adapter = AnimalsAdapter (this,listofAnimals)
        tvListAnimal.adapter= adapter;      // refer to activity main 's list viwto work with code
    }


    fun delete(index:Int){
        listofAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged(); // dont reassign adapter to prevent bug,  this line of code will reload the adapter

    }

    fun add(index:Int){
        listofAnimals.add(index,listofAnimals[index]);
        adapter!!.notifyDataSetChanged();
    }

    inner class  AnimalsAdapter:BaseAdapter {
        var listofAnimals = ArrayList<Animal>()
        var context:Context? = null;
        constructor(   context: Context,         listofAnimals : ArrayList<Animal>) : super(){
            this.listofAnimals = listofAnimals;
            this.context = context;
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View { // this method will be called getCount() times
            val animal = listofAnimals [p0];
            if (animal.isKiller==true){ // red border for if animal is killer
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                var myView = inflator.inflate(R.layout.animal_killer_ticket,null) ; // work with animal_ticket.xml in the code
                // convert animal_ticket to be accessed in code
                myView.tvName.text=animal.name!!;
                myView.tvDes.text=animal.des!!;
                myView.ivAnimalImage.setImageResource(animal.image!!);


                myView.ivAnimalImage.setOnClickListener {
//                    delete(p0);
//                      add(p0);

                    val intent =  Intent(context,AnimalInfo::class.java);//Intent takes 2 parameters, current activity which is context, secnd parameter is activity to go to , add ::class.java to take it as a java class
                    intent.putExtra("name",animal.name!!); // .putextra passes parameters in intent when going to an activity
                    intent.putExtra("des",animal.des!!);
                    intent.putExtra("image",animal.image!!);
                    context!!.startActivity(intent); //start acitivity animalinfo, add !! to prevent error nullsafety

                }

                return myView;

            }else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                var myView = inflator.inflate(R.layout.animal_ticket,null) ; // work with animal_ticket.xml in the code
                // convert animal_ticket to be accessed in code
                myView.tvName.text=animal.name!!;
                myView.tvDes.text=animal.des!!;
                myView.ivAnimalImage.setImageResource(animal.image!!);


                myView.ivAnimalImage.setOnClickListener {
//                    delete(p0);
//                    add(p0);

                    val intent =  Intent(context,AnimalInfo::class.java);//Intent takes 2 parameters, current activity which is context, secnd parameter is activity to go to , add ::class.java to take it as a java class
                    intent.putExtra("name",animal.name!!); // .putextra passes parameters in intent when going to an activity
                    intent.putExtra("des",animal.des!!);
                    intent.putExtra("image",animal.image!!);
                    context!!.startActivity(intent); //start acitivity animalinfo, add !! to prevent error nullsafety

                }

                return myView;

            }



        }

        override fun getItem(p0: Int): Any {
            return listofAnimals[p0];
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong();

        }


        override fun getCount(): Int { // count how many animals in the arraylist,
            return listofAnimals.size;
        }

    }

}
