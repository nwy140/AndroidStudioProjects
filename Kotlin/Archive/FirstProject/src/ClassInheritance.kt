

open class Operations() { // open , because class is final to allow inheritance
    public  var ProcessName:String?=null
    fun sum(n1:Int,n2:Int):Int{
        return n1+n2;
    }
    fun div(n1:Int,n2:Int):Int{
        return n1/n2;
    }

}

class MultiOperations():Operations() { //Inherite the class so you don't need to rewrite the methods
                                     // : in kotlin ,means inheritance
//    fun sum(n1:Int,n2:Int):Int{
//        return n1+n2;
//    }
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

    var op = Operations();
    var sum=op.sum(10,15);
    println("sum:"+sum);
    var div = op.div(12,11);
    println("div:"+div);
    op.ProcessName;

    // second
    var op2= MultiOperations();
     sum=op.sum(10,15);
    println("sum:"+sum);
     div = op.div(12,11);
    println("div:"+div);




}





