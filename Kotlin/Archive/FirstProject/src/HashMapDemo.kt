

fun main (args:Array<String>) {

    // putting values inside key variables

    var map = HashMap<Int,String>(); // key is integer, value is string
    map.put(1,"hussein");
    map.put(2,"jena");
    map.put(3,"laya");
    println( map.get(3) );

    map.put(3,"layaupdate");
    for (k in map.keys){
        println(map.get(k));
    }


}