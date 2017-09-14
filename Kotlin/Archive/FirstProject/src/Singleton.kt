
class Singleton{
    var name:String?=null;
    private constructor(){
        println("instance is created");
    }
companion object {
        val instance:Singleton by lazy { Singleton()}
    }
}

fun main (args:Array<String>){

    var s1 = Singleton.instance
    s1.name="hussein";
    println(s1.name);

    var s2 = Singleton.instance
    s2.name= "hussein";
    println(s2.name);

}