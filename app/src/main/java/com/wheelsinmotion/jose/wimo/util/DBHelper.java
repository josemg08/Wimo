package com.wheelsinmotion.jose.wimo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wheelsinmotion.jose.wimo.model.Badge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 12/3/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "wimoBasic";

    // Table Names
    private static final String TABLE_BADGE = "badges";
    private static final String TABLE_MONTHLY_ACCUMULATED = "monthly_accumulated";
    private static final String TABLE_DAY_ACCUMULATED = "day_accumulated";

    // Column names
    private static final String BADGE_ID = "badge_id";
    private static final String BADGE_NAME = "badge_name";

    private static final String BADGE_VALUE = "badge_value";
    private static final String BADGE_DRAWABLE_VALUE = "badge_drawable_value";

    private static final String MONTHLY_ACUUMULATED_ID = "monthly_accumulated_id";
    private static final String MONTHLY_ACUUMULATED_VALUE = "monthly_accumulated_value";

    // Table Create Statements
    // Badge table create statement
    private static final String CREATE_TABLE_BADGE = "CREATE TABLE "
            + TABLE_BADGE + "(" + BADGE_ID + " INTEGER PRIMARY KEY," + BADGE_NAME
            + " BADGE_NAME," + BADGE_VALUE + " BADGE_VALUE," + BADGE_DRAWABLE_VALUE
            + " BADGE_DRAWABLE_VALUE" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_BADGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BADGE);

        // create new tables
        onCreate(db);
    }

    /*
    * Creating a badge
    */
    public long createBadge (Badge badge) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BADGE_ID, badge.getId());
        values.put(BADGE_NAME, badge.getName());
        values.put(BADGE_VALUE, badge.getValue());
        values.put(BADGE_DRAWABLE_VALUE, badge.getDrawableValue());

        // insert row
        long badge_id = db.insert(TABLE_BADGE, null, values);

        return badge_id;
    }

    /*
    * get single badge by id
    */
    public Badge getBadge(long badge_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_BADGE + " WHERE "
                + BADGE_ID + " = " + badge_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Badge badge = new Badge(c.getLong(c.getColumnIndex(BADGE_ID)),
                                c.getString(c.getColumnIndex(BADGE_NAME)),
                                c.getInt(c.getColumnIndex(BADGE_VALUE)),
                                c.getInt(c.getColumnIndex(BADGE_DRAWABLE_VALUE)));

        return badge;
    }

    /*
    * get list of badges by value
    */
    public List<Badge> getBadges(int value) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Badge> badges = new ArrayList<Badge>();
        String selectQuery = "SELECT * FROM " + TABLE_BADGE + " WHERE " + BADGE_VALUE +" < "+ value;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Badge badge = new Badge(c.getLong(c.getColumnIndex(BADGE_ID)),
                        c.getString(c.getColumnIndex(BADGE_NAME)),
                        c.getInt(c.getColumnIndex(BADGE_VALUE)),
                        c.getInt(c.getColumnIndex(BADGE_DRAWABLE_VALUE)));

                // adding to badges list
                badges.add(badge);
            } while (c.moveToNext());
        }

        return badges;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}