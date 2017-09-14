package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>() //Arraylist referring to Animal class
    var adapter:AnimalsAdapter?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load animals
        listOfAnimals.add(Animal("Baboon", "Baboon live in a big place with tree",R.drawable.baboon))
        listOfAnimals.add(Animal("Bulldog", "Bulldog live in a big place with tree",R.drawable.bulldog))
        listOfAnimals.add(Animal("Panda", "Panda live in a big place with tree",R.drawable.panda))
        listOfAnimals.add(Animal("Swallow", "Swallow live in a big place with tree",R.drawable.swallow_bird))
        listOfAnimals.add(Animal("White tiger", "White tiger live in a big place with tree",R.drawable.white_tiger))
        listOfAnimals.add(Animal("Zebra", "Zebra live in a big place with tree",R.drawable.zebra))

        adapter = AnimalsAdapter(listOfAnimals);

    }

    // Base adapter is the adapter for listview , you need to inherit baseadaptor to change data in list view
    class  AnimalsAdapter:BaseAdapter{
        var listOfAnimals:ArrayList<Animal>()
        constructor(listOfAnimals: ArrayList<Animal>):super{
            this.listOfAnimals = listOfAnimals;
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[p0]
            var myView = layoutInflater.inflate(R.layout.animal_ticket,null)
            myView.tvName.text =  animal.name;
            myView.tvDes.text = animal.des
            myView.ivAnimalImage.setImageResource(animal.image);
            return  myView;
        }

        override fun getItem(p0: Int): Any {
            return  listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return  p0.toLong()
        }

        override fun getCount(): Int { //how many times getview runs
            return  listOfAnimals.size
        }

    }

}
