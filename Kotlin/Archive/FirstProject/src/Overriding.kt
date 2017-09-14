

open class Operations1 { // open , because class is final to allow inheritance
    constructor(Name:String="Nwy") ;

    public  var ProcessName:String?=null
   open fun sum(n1:Int,n2:Int):Int{
        return n1+n2;
    }
    fun div(n1:Int,n2:Int):Int{
        return n1/n2;
    }

}

class MultiOperations1 : Operations1() { //Inherite the class so you don't need to rewrite the methods
    // : in kotlin ,means inheritance


    //
//    constructor () : super() { // call super constructor
//        /////////////////
//    }
    override fun sum(n1:Int,n2:Int):Int{
        return n1+n2*3;
    }
//    fun div(n1:Int,n2:Int):Int{
//        return n1/n2;
//    }
    fun sub (n1:Int,n2:Int):Int{
        return n1-n2;
    }
    fun mul(n1:Int,n2:Int):Int{
        return n1*n2;
    }
    fun GetName(){
        super.ProcessName; // superclass
    }
}




fun main (args:Array<String>) {

    var op = Operations1();
    var sum=op.sum(10,15);
    println("sum:"+sum);
    var div = op.div(12,11);
    println("div:"+div);
    op.ProcessName;

    // second
    var op2= MultiOperations1() as Operations1; // MultiOperations1 is using ethods from operations11 rather than its own, unless it is overriding
    sum=op2.sum(10,15);
    println("sum:"+sum);
    div = op2.div(12,11);
    println("div:"+div);





}


/*


open class Operations1() { // open , because class is final to allow inheritance
    public  var ProcessName:String?=null
   open fun sum(n1:Int,n2:Int):Int{
        return n1+n2;
    }
    fun div(n1:Int,n2:Int):Int{
        return n1/n2;
    }

}

class MultiOperations1():Operations1() { //Inherite the class so you don't need to rewrite the methods
    // : in kotlin ,means inheritance
    override fun sum(n1:Int,n2:Int):Int{
        return n1+n2*3;
    }
//    fun div(n1:Int,n2:Int):Int{
//        return n1/n2;
//    }
    fun sub (n1:Int,n2:Int):Int{
        return n1-n2;
    }
    fun mul(n1:Int,n2:Int):Int{
        return n1*n2;
    }
    fun GetName(){
        super.ProcessName; // superclass
    }
}




fun main (args:Array<String>) {

    var op = Operations1();
    var sum=op.sum(10,15);
    println("sum:"+sum);
    var div = op.div(12,11);
    println("div:"+div);
    op.ProcessName;

    // second
    var op2= MultiOperations1();
    sum=op2.sum(10,15);
    println("sum:"+sum);
    div = op2.div(12,11);
    println("div:"+div);




}







 */



