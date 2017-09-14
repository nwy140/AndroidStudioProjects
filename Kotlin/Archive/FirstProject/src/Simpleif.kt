

fun main (args:Array<String>)   {

    println("Enter your grade");
    var grade = readLine()!!.toDouble();

    if (grade>=90){
        //block of code
        println(" You got an A*, while doing Mufy") ;
    } else if (grade>=80 && grade <90)
    {
        println(" You got an A, while doing Mufy") ;


    }else if (grade>=70 && grade <80)
    {
        println(" You got an B, while doing Mufy") ;


    }else if (grade>=60 && grade <70)
    {
        println(" You got an C, while doing Mufy") ;


    }




    else {
        //block of code
        println(" you are not doing well in Mufy");


    }




//    else if (grade>=50 && grade   <=70  ){
//        grade = grade +10;
//        println("You get extra 10 points");
//
//
//
//    }

    println(" you entered $grade" )













}