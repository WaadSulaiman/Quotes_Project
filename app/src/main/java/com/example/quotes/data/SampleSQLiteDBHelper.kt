package com.example.quotes.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class SampleSQLiteDBHelper(val context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE " + QUOTE_TABLE_NAME + " (" +
                    QUOTE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QUOTE_COLUMN_QUOTE + " TEXT, " +
                    QUOTE_COLUMN_AUTHOR + " TEXT " + ")"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUOTE_TABLE_NAME)
        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "database"
        const val QUOTE_TABLE_NAME = "Favorites"
        const val QUOTE_COLUMN_ID = "_id"
        const val QUOTE_COLUMN_QUOTE = "quote"
        const val QUOTE_COLUMN_AUTHOR = "author"
    }

    fun saveToDB(QUOTE: String, AUTHOR: String) {
        val database = SampleSQLiteDBHelper(context).writableDatabase
        val values = ContentValues()
        values.put(
            SampleSQLiteDBHelper.QUOTE_COLUMN_QUOTE,
            QUOTE
        )
        values.put(
            SampleSQLiteDBHelper.QUOTE_COLUMN_AUTHOR,
            AUTHOR
        )
        database.insert(SampleSQLiteDBHelper.QUOTE_TABLE_NAME, null, values)
        Log.d("Saved", "Item")
    }

    fun readFromDB(): Cursor {
        val database = SampleSQLiteDBHelper(context).readableDatabase
        val projection = arrayOf<String>(
            SampleSQLiteDBHelper.QUOTE_COLUMN_ID,
            SampleSQLiteDBHelper.QUOTE_COLUMN_QUOTE,
            SampleSQLiteDBHelper.QUOTE_COLUMN_AUTHOR,
        )

        val cursor: Cursor = database.query(
            SampleSQLiteDBHelper.QUOTE_TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        Log.d("TAG", "The total cursor count is " + cursor.getCount())
        return cursor
    }

    fun delete(_id: Long) {
        val database = SampleSQLiteDBHelper(context).writableDatabase
        database.delete(QUOTE_TABLE_NAME, QUOTE_COLUMN_ID + "=" + _id, null)
    }

}