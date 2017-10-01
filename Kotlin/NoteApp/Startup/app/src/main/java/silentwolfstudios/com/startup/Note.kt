package silentwolfstudios.com.startup

class Note{

    var nodeID:Int?=null;
    var nodeName:String?=null;
    var nodeDes:String?=null;

    constructor(nodeID:Int,nodeName:String,nodeDes:String){
        this.nodeID=nodeID;
        this.nodeName=nodeName;
        this.nodeDes=nodeDes;

    }

}


// setup app
// create inner class to pass parameter using base adapter
// implement members
// return unused functions
// return getCount using size of data arraylist
// use layout inflater to access and edit layout of ticket
// whatever in layout ticket = currentelement in arraylist's data
// create variable = adapter innerclass and pass arratlist
// make listview.adapter = variable created previously