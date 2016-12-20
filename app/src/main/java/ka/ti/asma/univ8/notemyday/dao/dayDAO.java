package ka.ti.asma.univ8.notemyday.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ka.ti.asma.univ8.notemyday.model.CriteriaDay;
import ka.ti.asma.univ8.notemyday.model.Day;
import ka.ti.asma.univ8.notemyday.dao.DBHelper;

/**
 * Created by abdelmadjidchaibi on 20/12/2016.
 */

public class DayDAO {
    public static final String TAG = "DayDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = { DBHelper.COLUMN_DAY_ID};

    public DayDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void createDay(Day day) {
        long insertId = mDatabase
                .insert(DBHelper.TABLE_DAYS, null, day.contentValues());
        Cursor cursor = mDatabase.query(DBHelper.TABLE_DAYS, mAllColumns,
                DBHelper.COLUMN_DAY_ID + " = " + insertId, null, null,
                null, null);
        System.out.println("cursor  content value"+cursor+day.contentValues());
        if (cursor.moveToFirst())
            cursorToDay(cursor);

        cursor.close();
    }

    public void insertDay(Day day) {
        mDatabase.insert(DBHelper.TABLE_DAYS, null,day.contentValues());
    }

    public void deleteDay(Day day) {
        String id = day.getDateString();
        // delete all CriteriaDay of this Day
        CriteriaDayDAO criteriaDayDAO = new CriteriaDayDAO(mContext);
        List<CriteriaDay> listCriteriaDay = criteriaDayDAO.getCriteriaDayOfDay(id);
        if (listCriteriaDay != null && !listCriteriaDay.isEmpty()) {
            for (CriteriaDay c : listCriteriaDay) {
                criteriaDayDAO.deleteCriteriaDay(c);
            }
        }

        System.out.println("the deleted Day has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_DAYS, DBHelper.COLUMN_DAY_ID
                + " = " + id, null);
    }

    public List<Day> getAllDays() {
        List<Day> listDays = new ArrayList<Day>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_DAYS, mAllColumns,
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Day Day = cursorToDay(cursor);
                listDays.add(Day);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listDays;
    }

    public Day getDayById(String id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_DAYS, mAllColumns,
                DBHelper.COLUMN_DAY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor.moveToFirst()) {
            Day Day = cursorToDay(cursor);
            return Day;
        }
      return null;
    }


    protected Day cursorToDay(Cursor cursor) {
        Day Day = new Day();
        Day.setDateString(cursor.getString(0));
        return Day;
    }
}

