package com.example.quotes.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SampleSQLiteDBHelper(val context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE " + QUOTE_TABLE_NAME + " (" +
                    QUOTE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QUOTE_COLUMN_QUOTE + " TEXT, " +
                    QUOTE_COLUMN_AUTHOR + " TEXT " + ")"
        )
        sqLiteDatabase.execSQL(
            "CREATE TABLE " + QUOTE_TABLE_OWN_NAME + " (" +
                    QUOTE_TABLE_OWN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    QUOTE_TABLE_OWN_QUOTE + " TEXT, " +
                    QUOTE_TABLE_OWN_AUTHOR + " TEXT " + ")"
        )

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $QUOTE_TABLE_NAME")
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $QUOTE_TABLE_OWN_NAME")

        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "database"
        const val QUOTE_TABLE_NAME = "Favorites"
        const val QUOTE_COLUMN_ID = "_id"
        const val QUOTE_COLUMN_QUOTE = "quote"
        const val QUOTE_COLUMN_AUTHOR = "author"

        const val QUOTE_TABLE_OWN_NAME = "Own"
        const val QUOTE_TABLE_OWN_ID = "_id"
        const val QUOTE_TABLE_OWN_QUOTE = "quote"
        const val QUOTE_TABLE_OWN_AUTHOR = "author"
    }

    fun checkIfQuote(QUOTE: String, AUTHOR: String) {
    }

    fun saveToDB(QUOTE: String, AUTHOR: String) {
        val database = SampleSQLiteDBHelper(context).writableDatabase
        val values = ContentValues()
        values.put(
            QUOTE_COLUMN_QUOTE,
            QUOTE
        )
        values.put(
            QUOTE_COLUMN_AUTHOR,
            AUTHOR
        )
        database.insert(QUOTE_TABLE_NAME, null, values)
    }

    fun saveToDBTableOwn(QUOTE: String, AUTHOR: String) {
        val database = SampleSQLiteDBHelper(context).writableDatabase
        val values = ContentValues()
        values.put(
            QUOTE_TABLE_OWN_QUOTE,
            QUOTE
        )
        values.put(
            QUOTE_TABLE_OWN_AUTHOR,
            AUTHOR
        )
        database.insert(QUOTE_TABLE_OWN_NAME, null, values)
    }

    fun readFromDBTableOwn(): Cursor {
        val database = SampleSQLiteDBHelper(context).readableDatabase
        val projection = arrayOf<String>(
            QUOTE_TABLE_OWN_ID,
            QUOTE_TABLE_OWN_QUOTE,
            QUOTE_TABLE_OWN_AUTHOR,
        )

        return database.query(
            QUOTE_TABLE_OWN_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun readFromDB(): Cursor {
        val database = SampleSQLiteDBHelper(context).readableDatabase
        val projection = arrayOf<String>(
            QUOTE_COLUMN_ID,
            QUOTE_COLUMN_QUOTE,
            QUOTE_COLUMN_AUTHOR,
        )

        return database.query(
            QUOTE_TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun removeFromTableOwn(id: Int): Boolean {
        val database = SampleSQLiteDBHelper(context).writableDatabase
        return database.delete(QUOTE_TABLE_OWN_NAME, "$QUOTE_TABLE_OWN_ID=$id", null) > 0
    }

    fun remove(id: Int): Boolean {
        val database = SampleSQLiteDBHelper(context).writableDatabase
        return database.delete(QUOTE_TABLE_NAME, "$QUOTE_COLUMN_ID=$id", null) > 0
    }


}
