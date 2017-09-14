abstract class CreditCard(){
    fun CreditID():String {

        return "827372whdsbdjk" ;
    }
    abstract fun newCredit()
}

class UserInfo():CreditCard(){
    fun getInfo():String{
        return CreditID();
    }
    override  fun newCredit(){
        println("new cart created")
    }
}

fun main (args:Array<String>){
//    var credit=CreditCard();
//    println(credit.CreditID());
    var user = UserInfo();
    println(user.getInfo());
}