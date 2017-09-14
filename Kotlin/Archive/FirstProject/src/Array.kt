

fun main (args:Array<String>) {

    var arrayInt=Array<Int>(5){0}; // while declaring array with type, use <type> , use (5) rather than [5] only when declaring
    arrayInt[3]=55;
    println ("Array[3]=" + arrayInt[3]);

    println("All element")
    for (element in arrayInt) {
        println(element);

    }
    println("All element by index");
    for (index in 0..4){
        println (arrayInt[index])
    }

    var arrayStr = Array<String>(4){""}
    for (index in 0..3){
        print("arrayStr[ $index ] ");
        arrayStr[index]= readLine()!!;
    }

    for (index in 0..3) {
        println("arrayStr[ $index ]=" + arrayStr[index])

    }


}