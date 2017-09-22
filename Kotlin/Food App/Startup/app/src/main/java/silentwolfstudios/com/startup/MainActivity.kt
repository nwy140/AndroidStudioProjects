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

    var listOfFoods = ArrayList<Food>();
    var adapter:FoodAdapter?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load drinks
        listOfFoods.add(Food("Plain Water","Feed your thirst",R.drawable.title));
        listOfFoods.add(Food("Ryokucha","Special Japanese Green Tea, which you can refill as many times as you like",R.drawable.title))

        //load Foods

        listOfFoods.add(Food("Cheesy Chicken Fry","Very tasty food that can fill up your stomach in no time", R.drawable.cheesychikcenfry));
        listOfFoods.add(Food("Omu Yakisoba","Some high quality nice eggs and ramen, specially made and prepared for you" , R.drawable.omuyakisoba ))
        listOfFoods.add(Food("Shirasu Okonomiyaki","Eat this, if you haven't do so",R.drawable.shirasuokonomiyaki))
        listOfFoods.add(Food("Takoyaki","",R.drawable.takoyaki))
        listOfFoods.add(Food("Unagi Yakimeshi","",R.drawable.unagiyakimeshi))

        adapter= FoodAdapter(this, listOfFoods);
        gvListFood.adapter = adapter;

//      listOfFoods.add(Food("","",R.drawable))

    }
    class FoodAdapter:BaseAdapter {
        var listOfFoods = ArrayList<Food>();
        var context:Context?=null;
        constructor(context:Context, listofFood:ArrayList<Food> ):super () {
            this.context=context;
            this.listOfFoods=listOfFoods;
        }
        override fun getView(p0: Int, p1:    View?, p2: ViewGroup?): View {
            val food = this.listOfFoods[p0];

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_ticket,null);
            foodView.ivFoodImage.setImageResource(food.image!!);
            foodView.tvName.text = food.name!!;
            return  foodView
        }

        override fun getItem(p0: Int): Any {
            return listOfFoods[p0];
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int { // call getview listOfFoods.size times
            return listOfFoods.size;
        }

    }

}
