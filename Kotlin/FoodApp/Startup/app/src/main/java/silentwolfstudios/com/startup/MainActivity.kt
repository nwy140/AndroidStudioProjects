package silentwolfstudios.com.startup

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {


    var adapter:FoodAdapters?=null;
    var listofFoods = ArrayList<Food>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load Foods
        listofFoods.add(  Food("Coffee", "Very  hot and tasty",R.drawable.coffee_pot));
        listofFoods.add( Food("Espersso","Some sort of tasty coffee",R.drawable.espresso));
        listofFoods.add(  Food("French Fries","Nicely done, no black dots and very long",R.drawable.french_fries)      );
        listofFoods.add(Food("Honey","Sweet and stolen",R.drawable.honey));
        listofFoods.add(Food(   "Strawberry","Nice pink fruit",R.drawable.strawberry_ice_cream));
        listofFoods.add(  Food("Sugar cubes","Very sweet stuff" , R.drawable.sugar_cubes) );
        adapter = FoodAdapters(this,listofFoods);
        gvListFood.adapter = adapter;



    }

    class FoodAdapters:BaseAdapter{
        var listofFoods = ArrayList<Food>();
        var context:Context?=null;
        constructor(context: Context,listofFoods:ArrayList<Food>):super(){
            this.context=context;
            this.listofFoods = listofFoods;
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val food = listofFoods[p0];

            var inflator= context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
            var foodView = inflator.inflate (R.layout.food_ticket,null) ;
            foodView.ivFoodImage.setImageResource( food.image!!);
            foodView.tvName.text = food.name!!;
            return  foodView
        }

        override fun getItem(p0: Int): Any {
            return  listofFoods[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong();
        }

        override fun getCount(): Int {
            return  listofFoods.size;
        }

    }


}
