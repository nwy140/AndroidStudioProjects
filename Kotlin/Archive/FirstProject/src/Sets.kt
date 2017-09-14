


fun main (args:Array<String>) {
//mutable list / set can be modified, otherwise not
    var setElement = setOf(1,2,3,11,45,55);
    for (element in setElement) {
        println(element);

    }
    var setEmenetM = mutableSetOf(1,2,3,11,44,55,55);
    setEmenetM.add(77);
    for (element in setEmenetM) {
        println(element);

    }


















}