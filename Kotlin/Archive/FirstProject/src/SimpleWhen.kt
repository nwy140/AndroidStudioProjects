



fun main (args:Array<String>) {

    print("Enter x value :");
    var x = readLine()!!.toInt();

    when (x) {

        0-> print ("value is 0");
        1,2-> print("value is 1 or 2");   // use {} if you have more than 1 line
        in 10..30-> print("value is 2");
        !in 10..30-> print("value is 3");
        else -> {

            print("value is out of range");
        }

    }



}