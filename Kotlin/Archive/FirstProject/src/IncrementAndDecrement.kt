import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg

/*
Operations rules| Priorities
 1-()
 2- ++, -- (before Var)
 3- ^
 4- *,/
 5- +,-
 6- =
 7- ++,-- (After Var)
 */

fun main (args:Array<String>) {

    var x=10;
    var y=11;
    var z= ++x+y; // (++x)=(x=x+1)
    println("z:$z");

    var m=x+++y; // x++ means x increase after operations are done
    println("m:$m");
    println("After x:$x");






}