package silentwolfstudios.com.startup

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        listofAnimals.add(Animal("Baboon","Baboon lives in big place with tree" , R.drawable.baboon,false))
        listofAnimals.add(Animal("BullDog","BullDog lives in big place with tree" , R.drawable.bulldog,false))

        listofAnimals.add(Animal("Panda","Panda lives in big place with tree" , R.drawable.panda,true))
        listofAnimals.add(Animal("Swallow","Swallow lives in big place with tree" , R.drawable.swallow_bird,false))
        listofAnimals.add(Animal("White Tiger","White Tiger lives in big place with tree" , R.drawable.white_tiger,true))
        listofAnimals.add(Animal("Zebra","Zebra lives in big place with tree" , R.drawable.zebra,false))

        adapter = AnimalsAdapter (this,listofAnimals)
        tvListAnimal.adapter= adapter;      // refer to activity main 's list viwto work with code
    }

    class  AnimalsAdapter:BaseAdapter {
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
                    val intent = Intent (context, AnimalInfo::class.java)
                    intent.putExtra("name", animal.name);
                    intent.putExtra("des",animal.des);
                    intent.putExtra("animal",animal.image);


                }

                return myView;

            }else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                var myView = inflator.inflate(R.layout.animal_ticket,null) ; // work with animal_ticket.xml in the code
                // convert animal_ticket to be accessed in code
                myView.tvName.text=animal.name!!;
                myView.tvDes.text=animal.des!!;
                myView.ivAnimalImage.setImageResource(animal.image!!);
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
