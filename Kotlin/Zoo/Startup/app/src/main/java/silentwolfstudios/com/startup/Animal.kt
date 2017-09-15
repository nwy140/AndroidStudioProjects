package silentwolfstudios.com.startup

import android.media.Image

class Animal { //whenever you have an object, you will create an animal class of that object which stores details about that animal object

    var name : String?=null;
    var des : String?=null;
    var image : Int? = null;
    var isKiller:Boolean? = null;

    constructor(name:String , des:String, image: Int, isKiller:Boolean){
        this.name=name;
        this.des=des;
        this.image=image;
        this.isKiller=isKiller
    }


}