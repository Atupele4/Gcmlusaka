package com.mboyaa.gcmlusaka;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "appnotice";

    // Contacts table name
    private static final String TABLE_CONTACTS = "notify";
    private static final String TABLE_FOLLOW = "FollowTable";

    // Contacts Table Columns names
    public static final String KEY_ID = "_id";
    public static final String KEY_COM_ID = "com_id";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_MESSAGE_STATUS = "msg_status";
    public static final String KEY_DATETIME = "datetimesent";



    // Table Create Statements
    // Todo table create statement
    private static final  String CREATE_MSGS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_COM_ID + " TEXT,"
            + KEY_MESSAGE + " TEXT,"
            + KEY_MESSAGE_STATUS + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";
    
   // Todo table create statement
    private static final  String CREATE_FOLLOWS_TABLE = "CREATE TABLE " + TABLE_FOLLOW + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_COM_ID + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";
    
   // Todo table create statement
    private static final  String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_COM_ID + " TEXT,"
            + KEY_MESSAGE + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";


    

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
       
        db.execSQL(CREATE_MSGS_TABLE);
        db.execSQL(CREATE_FOLLOWS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOLLOW);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
   public void saveMessage(Messages contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COM_ID, contact.get_com_id()); // Contact Name
        values.put(KEY_MESSAGE, contact.get_message()); // Contact Phone
        values.put(KEY_DATETIME, contact.get_dateTime()); // Contact Phone
        values.put(KEY_MESSAGE_STATUS, "1"); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

   /* // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

    // Getting All Contacts
    public Cursor getAllContacts() {
        List<Messages> contactList = new ArrayList<Messages>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " ORDER BY datetimesent ASC;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Messages contact = new Messages();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.set_com_id(cursor.getString(1));
                contact.set_message(cursor.getString(2));
                contact.set_datetime(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
            //return cursor;
        }

        // return contact list
        return cursor;
    }

    // Updating single contact
    public int updateMessageViewed(String msgID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE_STATUS, 2);

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ? ",
                new String[] { String.valueOf(msgID) });
    }

    // Deleting single contact
    public void deleteContact(Messages contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    // Deleting single contact
    public void deleteMessage(String message_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(message_id) });
        db.close();
    }

    // Deleting single contact
    public void ClearNotificationsMessage(String message_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " != ?",
                new String[] { String.valueOf(message_id) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting contacts Count
    public Cursor getCompCount(String com_id) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS + " WHERE com_id ='" + com_id + "' ORDER BY " + KEY_MESSAGE_STATUS + " ASC;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor;
    }


    public Cursor getCompUnreadMessages(String com_id) {
        String countQuery = "SELECT " + KEY_MESSAGE_STATUS + " FROM " + TABLE_CONTACTS + " WHERE com_id ='" + com_id + "' and " + KEY_MESSAGE_STATUS + " = '1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor;
    }

}