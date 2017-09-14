
fun sum(n1:Double , n2:Double):Double{
    // block of code

    var sum=n1+n2;
    return sum;
}

fun display(n:Int=0):Unit { // Unit is the same as void, don't return anything
    println("n:$n");        // n = 0 is default value is user doesn't add in any value in parameters for display

}
fun main(args:Array<String>) {


    var r = sum(10.0,12.0);
    println("r:$r");

    r = sum(1.0,2.0);
    println("r:$r");

    r = sum(50.0,20.0);
    println("r:$r");

    display(1000)

























































}
