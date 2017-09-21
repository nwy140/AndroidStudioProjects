package silentwolfstudios.com.startup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        val bundle: Bundle = intent.extras //accepting passing data for intent from main acitivity that changes to animalinfo activity
        val name = bundle.getString("name"); // bundle acts like a constructor for activity ,
        val des = bundle.getString("des");
        val image = bundle.getInt("image"); //get integer because    image id is passed
        ivAnimalImage.setImageResource(image);
        tvName.text = name;
        tvDes.text = des;
    }
}
