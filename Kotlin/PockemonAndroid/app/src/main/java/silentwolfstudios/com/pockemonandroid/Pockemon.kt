package silentwolfstudios.com.pockemonandroid

import android.location.Location

/**
 * Created by chia on 11/9/2017.
 */

class Pockemon{

    var name:String?=null;
    var des:String?=null;
    var image:Int?=null;
    var power:Double?=null;
    var location:Location?=null;
//    var lat:Double?=null;
//    var log:Double?=null;


    var IsCatch:Boolean?=false;

    constructor(image:Int, name:String,des:String,power:Double,lat:Double,log:Double){
        this.name=name; //name = incoming name
        this.des=des;
        this.image=image;
        this.power=power;

        this.location= Location(name)
        this.location!!.latitude=lat
        this.location!!.longitude=log
        this.IsCatch=   false;

    }



}