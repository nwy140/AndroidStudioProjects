package silentwolfstudios.com.startup

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DbManager {

    val dbName="MyNotes"
    val dbTable="Notes"
    val colID="ID"
    val colTitle="Title"
    val colDes="Description"
    val dbVersion=1; // version number
    //CREATE TABLE IF NOT EXISTS MyNotes (ID INTEGER PRIMARY KEY , title TEXT, Description TEXT);"
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS" + dbTable + "("+colID + "INTEGER PRIMARY KEY," +
            colTitle + " TEXT, " + colDes + " TEXT):";
    var sqlDB:SQLiteDatabase?=null;

    //when application starts, constructor will call it
    constructor(context: Context){
        var db=DatabaseHelperNotes(context  ); // when database manager reated, it call helpernotes class, which will created the database that is not available.
        sqlDB=db.writableDatabase;



    }

    //this SQLiteOpenHelper inherited class create database and execute commands in database
    inner class DatabaseHelperNotes:SQLiteOpenHelper {

        var context: Context? = null;

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context;
        }

        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(sqlCreateTable) //sql statement  run
            Toast.makeText(this.context, " database is created", Toast.LENGTH_LONG).show()
            //when sql created show name of sql database
        } //7867867857

        //when someone update datapase version
        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("Drop table IF EXIST" + dbTable)
        }
    }

    fun Insert(values: ContentValues):Long{

        val ID=sqlDB!!.insert(dbTable,"",values);
        return ID;
    }
    //---Create new Query function
    fun Query(projection:Array<String>,selection:String,selectionArgs:Array<String>,SorOrder:String):Cursor{
        //projection means number of column,selection means which column to choose,  Cursor looks like table and move through data

        val qb=SQLiteQueryBuilder();
        qb.tables=dbTable; //table name
        val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,SorOrder);
        return cursor
    }
    //---Create new Query function---


}