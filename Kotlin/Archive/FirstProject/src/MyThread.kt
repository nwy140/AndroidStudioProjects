

fun  main (args:Array<String>) {

    var t1=thread("thread1");
    t1.start();
//    println("   thread is run");

    var t2=thread("thread2");
    t2.start();
//    println("   thread is run");

    var t3=thread("thread3");
    t3.start();
    t3.join();
    println("   thread is run");

}

class thread():Thread(){
    var ThreadName:String="";

    constructor(ThreadName:String):this(){
        this.ThreadName=ThreadName;
        println(this.ThreadName +  " is started");
    }

    override fun run(){
        //Write Thread

        var count=0;
        while (count<10){
            println("$ThreadName  Count:$count");
            count++;

            try {
                Thread.sleep(1000) //1000 milisec = 1 second
            }catch (ex:Exception){
                println(ex.message);
            }
            }

    }

}