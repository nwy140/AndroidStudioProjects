


fun main (args:Array<String>) {

    var n1=10;
    var n2=20;
    var max= if(n2>n2) n1 else n2; // this means if condition satisfies, n1 is returned , else n2 is returned

    println("max:$max");

    //When
    var age=30

    var isYoung = when(age){
        30-> true;
        else->false;

    }
    print ("isYoung:$isYoung");




}