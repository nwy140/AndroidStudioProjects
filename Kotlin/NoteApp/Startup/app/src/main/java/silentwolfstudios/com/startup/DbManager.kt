package silentwolfstudios.com.startup

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.text.Selection
import android.widget.Toast
import java.nio.channels.SelectionKey

class DbManager{

//---sqlite database interfaces
    val dbName="MyNotes"
    val dbTable="Notes"

    //column
    val colID="ID";
    val colTitle="Title";
    val colDes="Description";

    //version
    val dbVersion=1;

    //sqlstatement
    //CREATE TABLE IF NOT EXIST NOTES (ID INTEGER PRIMARY KEY title TEXT, Description TEXT);
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+ dbTable+ " ("+colID+
                        " INTEGER PRIMARY KEY," +colTitle+ " TEXT, "+colDes+" TEXT);"
    var sqlDB:SQLiteDatabase?=null;
//--sqlite database interface---
    constructor(context:Context){
        var db=DatabaseHelperNotes(context);
         sqlDB=db.writableDatabase;
    }
    inner class DatabaseHelperNotes:SQLiteOpenHelper{
        var context:Context?=null
        constructor(context:Context):super(context,dbName,null,dbVersion)   {
            this.context=context;
        }

        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context," database is created", Toast.LENGTH_LONG).show()   ;
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("Drop table IF EXISTS "+dbTable)
        }
    }

    fun Insert(values:ContentValues):Long{
        val  ID=sqlDB!!.insert(dbTable,"",values)
        return ID
    }
    //---Create Query Function
    fun Query(projecion:Array<String>,selection: String,selectionArgs:Array<String>,sorOrder:String):Cursor{
        //projection is number of columns, selection is colTitle or colDes selection,
        val qb=SQLiteQueryBuilder()
        qb.tables=dbTable;
        val cursor = qb.query(sqlDB,projecion,selection,selectionArgs,null,null,sorOrder)
        return cursor
    }
    //---Create Query Function---

    //---Delete sql fuction
    fun Delete(selection:String,selectionArgs: Array<String>):Int{

        val count=sqlDB!!.delete(dbTable,selection,selectionArgs)
        return count
    }
    //---Delete sql function---



}
