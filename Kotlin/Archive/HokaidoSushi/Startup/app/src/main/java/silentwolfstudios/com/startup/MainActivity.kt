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
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {


    var adapter:FoodAdapters?=null;
    var listofFoods = ArrayList<Food>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load Foods
        listofFoods.add(  Food("Ryokucha\n" +
                " Price:RM1.25", "Refilable, and tasty",R.drawable.coffee_pot));

        listofFoods.add(Food("Cheesy Chicken Fry\nPrice:RM13.80","Very tasty food that can fill up your stomach in no time", R.drawable.cheesychikcenfry));
        listofFoods.add(Food("Omu Yakisoba\n" +
                " Price:RM14.80","Some high quality nice eggs and ramen, specially made and prepared for you" , R.drawable.omuyakisoba ))
        listofFoods.add(Food("Shirasu Okonomiyaki\n" +
                " Price:RM15.80","Eat this, if you haven't do so\nPrice:",R.drawable.shirasuokonomiyaki))
        listofFoods.add(Food("Takoyaki\n" +
                " Price:RM8.80","Nice food, that you want to eat\nPrice:",R.drawable.takoyaki));
        listofFoods.add(Food("Unagi Yakimeshi\n" +
                " Price:RM19.80","Nice and expensive, becareful of tiny bones though\nPrice:",R.drawable.unagiyakimeshi));



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

            foodView.ivFoodImage.setOnClickListener{
                val intent = Intent(context,FoodDetails::class.java)
                intent.putExtra(    "name",food.name!!);
                intent.putExtra("des",food.des!!);
                intent.putExtra("image",food.image!!);
                context!!.startActivity(intent);
            }
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









