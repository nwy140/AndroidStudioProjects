package silentwolfstudios.com.startup

class Animal{

    var name:String?=null
    var des:String?=null
    var image:String?=null // image is int type because it is the id of image

    constructor(name:String,des:String,Image:Int){
        // pass constructor parameters to declared variables in this class
        this.name=name
        this.des=des
        this.image=image
    }

}