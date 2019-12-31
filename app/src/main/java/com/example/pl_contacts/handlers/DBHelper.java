package com.example.pl_contacts.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pl_contacts.instances.Contact;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "pl.db";
    public static final String TBL_Contacts = "contacts";
    public static final String TBL_Numbers = "numbers";
    public static final String TBL_Groupe = "groupp";
    public static final String TBL_GroupContacts = "group_contacts";
//    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_Contacts + "(" +
                " id INTEGER PRIMARY KEY AutoIncrement," +
                " firstname VARCHAR(20)," +
                " lastname VARCHAR(30)," +
                " alias VARCHAR(30)," +
                " workaddress TEXT," +
                " homeaddress TEXT," +
                " website VARCHAR(70)," +
                " email VARCHAR(50)," +
                " dateofbirth VARCHAR(10)," +
                " profilepicture INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_Numbers + "(" +
                " id INTEGER," +
                " type VARCHAR(20)," +
                " number VARCHAR(30)," +
                "FOREIGN KEY(id) REFERENCES contacts(id))");

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TBL_Groupe + "(" +
                " id INTEGER PRIMARY KEY AutoIncrement," +
                " name Text" + ")"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TBL_GroupContacts + "(" +
                " id_group INTEGER," +
                " id_contact INTEGER," +
                "FOREIGN KEY(id_group) REFERENCES groupp(id)," +
                "FOREIGN KEY(id_contact) REFERENCES contacts(id)" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertContacts(String firstname, String lastname, String alias, String workAddress, String homeAddress, String website, String email, String dateOfBirth, int profilePicture){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("alias", alias);
        contentValues.put("workaddress", workAddress);
        contentValues.put("homeaddress", homeAddress);
        contentValues.put("website", website);
        contentValues.put("email", email);
        contentValues.put("dateofbirth", dateOfBirth);
        contentValues.put("profilepicture", profilePicture);

        long response = db.insert(TBL_Contacts, null, contentValues);
        if (response != -1) {
            return true;
        } else {
            return false;
        }
    }


    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TBL_Contacts,null);

        List<Contact> contactList = new ArrayList<>();

        while (res.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(res.getInt(0));
            contact.setFirstName(res.getString(1));
            contact.setLastName(res.getString(2));
            contact.setNickName(res.getString(3));
            contact.setWorkPlaceAddress(res.getString(4));
            contact.setHouseAddress(res.getString(5));
            contact.setWebsite(res.getString(6));
            contact.setEmail(res.getString(7));
            contact.setBirthDate(res.getString(8));
            contact.setImage(res.getInt(9));
            contactList.add(contact);
        }

        return contactList;
    }


    public boolean insertPhoneNumber(int contactId, String number, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", contactId);
        contentValues.put("type", type);
        contentValues.put("number", number);

        long insert = db.insert(TBL_Numbers, null, contentValues);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Contact getContact(int id) {
        Contact contact = new Contact();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery(
                "SELECT numbers.number FROM " + TBL_Contacts +
                " INNER JOIN " + TBL_Numbers + " ON " + "contacts.id = numbers.id "
//                "WHERE contacts.id = 1;"
                ,null);

        Log.i("ok shit", "getContact: " + res);




        return contact;
    }



}