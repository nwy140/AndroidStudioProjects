import javax.jws.soap.SOAPBinding

class UserAdmins<T> (credit:T){

    init{
        println(credit)
    }
// T is template , but you can put any other  too

}

fun <T> display(process:T){
    println(process);
}

fun main(args:Array<String>){
    var userAdmin = UserAdmins<String>("NWY");
    var userAdmin2= UserAdmins<Int>(123);
    var userAdmin3 = UserAdmins<Double>(1.23);

    display<Int>(22)
    display<String>("process user")
}


