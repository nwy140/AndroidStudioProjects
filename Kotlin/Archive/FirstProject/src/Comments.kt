
// This main function
fun main(args:Array<String>){

    print("Enter name:");
    //Enter user name

    var name= readLine()!!.toString(); // var means you can read and write on it
    // val means read only

    print("Enter age:");
    var age:Int= readLine()!!.toInt()   ; // you dn't really have to add data type in kotlin , but it is a good practice to declare using data type
    print("Enter department:");
    var dep:String?; // when ? is added , it means declared as null value
    dep= readLine()!!.toString();

    print("Enter PI:");
    var pi:Double= readLine()!!.toDouble()    ;

    /* print output
        to allow users to see the variables values
     */

    println("**** output ****") ;

    println("name:"+name)   ;
    println("age:"+age) ;
    println("department:" + dep)    ;
    print ("PI:" + pi);



//    var x=10;
//    var y=12;

}









