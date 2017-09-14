

fun main (args:Array<String>) {

//    for (count in 1..10) {
//        if (count == 4) {
////            continue // skip current loop for once
//            break // break/destroy the current loop
//        }
   loop@ for (count in 1..10) {

        for (count2 in 1..5) {
            println("countL$count");
            if (count==2) {
                break@loop // add @loop label to break external loops
            }
        }
    }




        println("loop done");








}