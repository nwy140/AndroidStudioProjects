package silentwolfstudios.com.startup


class SongInfo{

    var Title:String?
    var AuthorName:String?=null
    var SongURL:String?=null
    constructor(Title:String,AuthorName:String,SongURL:String){
        this.Title=Title;
        this.AuthorName=AuthorName;
        this.SongURL=SongURL;
    }

}
