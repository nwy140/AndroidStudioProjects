

fun main (args:Array<String>) {
// difference between arrayList and array is that
    // arraylist does not have size and is not restricted by size
    //use arraylist.get() to get elements in arrayList
    //most android data is saved in arraylist so we will use it alot
    var arraylist = ArrayList<String>() ;
    arraylist.add("jena");
    arraylist.add("laya");
    arraylist.add("hussein");
    arraylist.add("ahmed");

    println("First name:" + arraylist.get(0));
    arraylist.set(0,"Laya Hussein        ") ;

    println(" all element");
    for (item in arraylist){
        print(item+"\n");
    }

    for ( index in 0..arraylist.size-1){
        println(arraylist.get(index));
    }



    if (arraylist.contains("hussein")) {
        println("name is found")
    }
    else {
        println("name is found");
    }


}