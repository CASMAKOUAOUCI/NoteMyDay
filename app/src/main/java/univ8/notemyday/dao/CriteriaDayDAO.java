package univ8.notemyday.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import univ8.notemyday.model.CriteriaDay;
import univ8.notemyday.model.Day;

/**
 * Created by Asma  on 20/12/2016.
 */

public class CriteriaDayDAO {
    public static final String TAG = "CriteriaDayDAO";

    private Context mContext;

    // Database fields
    private static SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_CRITERIADAY_ID,
            DBHelper.COLUMN_CRITERIADAY_NAME,
            DBHelper.COLUMN_CRITERIADAY_DESCRIPTION,
            DBHelper.COLUMN_CRITERIADAY_RATING,
            DBHelper.COLUMN_CRITERIADAY_DAY_ID};

    public CriteriaDayDAO(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
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

    public void createCriteriaDay(CriteriaDay criteriaDay) {
        long insertId = mDatabase
                .insert(DBHelper.TABLE_CRITERIADAY, null, criteriaDay.contentValue());

        Cursor cursor = mDatabase.query(DBHelper.TABLE_CRITERIADAY, mAllColumns,
                DBHelper.COLUMN_CRITERIADAY_ID + " = " + insertId, null, null,
                null, null);
        if (cursor.moveToFirst())
            cursorToCriteriaDay(cursor);
        cursor.close();
    }

    public List<CriteriaDay> getCriteriaDayOfDay(String id) {
        List<CriteriaDay> listCriteriaDay = new ArrayList<CriteriaDay>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_CRITERIADAY, mAllColumns,
                DBHelper.COLUMN_CRITERIADAY_DAY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CriteriaDay criteriaDay = cursorToCriteriaDay(cursor);
            listCriteriaDay.add(criteriaDay);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listCriteriaDay;
    }

    public CriteriaDay getCriteriaDay(long id) {
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CRITERIADAY, mAllColumns,
                DBHelper.COLUMN_CRITERIADAY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);

        cursor.moveToFirst();
        CriteriaDay criteriaDay = cursorToCriteriaDay(cursor);
        // make sure to close the cursor
        cursor.close();
        return criteriaDay;
    }

    public void insertCriteriaDay(CriteriaDay criteriaDay) {
        long id = criteriaDay.getId();
        System.out.println("the inserted criteria has the id: " + id);
        mDatabase.insert(DBHelper.TABLE_CRITERIADAY,null,criteriaDay.contentValue());
    }

    public void  deleteCriteriaDay(CriteriaDay criteriaDay) {
        long id = criteriaDay.getId();
        System.out.println("the deleted criteria has the id: " + id);
        mDatabase.delete(DBHelper.TABLE_CRITERIADAY, DBHelper.COLUMN_CRITERIADAY_ID
                + " = " + id, null);
    }

    public void updateCriteriaDay(CriteriaDay criteriaDay) {
        mDatabase.update(DBHelper.TABLE_CRITERIADAY, criteriaDay.contentValue(), DBHelper.COLUMN_CRITERIADAY_ID+" = "+criteriaDay.getId(), null);
    }

    private CriteriaDay cursorToCriteriaDay(Cursor cursor) {
        CriteriaDay criteriaDay = new CriteriaDay();
        criteriaDay.setId(cursor.getInt(0));
        criteriaDay.setName(cursor.getString(1));
        criteriaDay.setDescription(cursor.getString(2));
        criteriaDay.setRating(cursor.getFloat(3));
        // get The Day by id
        String dayDateId = cursor.getString(4);
        criteriaDay.setDateString(dayDateId);
        DayDAO dao = new DayDAO(mContext);
        Day day = dao.getDayById(dayDateId);
        if (day != null)
            criteriaDay.setDay(day);

        return criteriaDay;
    }


}
