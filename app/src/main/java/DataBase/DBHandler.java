package DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentInfo.db";

    public static final String TABLE_STUDENTS = "Students";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_REG_NO = "regNo";
    public static final String COLUMN_PHONE_NO = "phoneNo";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_GENDER_ID = "gender_id";
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_DEPARTMENT_ID = "department_id";

    private static final String TAG = "ColumnSize";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_STUDENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE_NO + " TEXT, " +
                COLUMN_REG_NO + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_BIRTHDAY + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_GENDER_ID + " TEXT, " +
                COLUMN_DEPARTMENT + " TEXT, " +
                COLUMN_DEPARTMENT_ID + " TEXT " +
                "); ";
        db.execSQL(query);
       // db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
        db.close();
    }


    public void addStudent(Students students) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, students.get_name());
        values.put(COLUMN_PHONE_NO, students.get_phoneNo());
        values.put(COLUMN_REG_NO, students.get_regNo());
        values.put(COLUMN_EMAIL, students.get_email());
        values.put(COLUMN_BIRTHDAY, students.get_birthday());
        values.put(COLUMN_GENDER, students.get_gender());
        values.put(COLUMN_GENDER_ID, students.get_gender_id());
        values.put(COLUMN_DEPARTMENT, students.get_department());
        values.put(COLUMN_DEPARTMENT_ID, students.get_department_id());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STUDENTS, null, values);
        db.close();

    }

    public void updateStudent(Students students, String _id) {
        /*String query = "UPDATE " + TABLE_STUDENTS + " SET " +
                COLUMN_NAME + "=" + students.get_name() + ", " +
                COLUMN_REG_NO + "=" + students.get_regNo() + ", " +
                COLUMN_PHONE_NO + "=" + students.get_phoneNo() + ", " +
                COLUMN_EMAIL + "=" + students.get_email() + ", " +
                COLUMN_BIRTHDAY + "=" + students.get_birthday() + ", " +
                COLUMN_GENDER + "=" + students.get_gender() + ", " +
                COLUMN_GENDER_ID + "=" + students.get_gender_id() + ", " +
                COLUMN_DEPARTMENT + "=" + students.get_department() + ", " +
                COLUMN_DEPARTMENT_ID + "=" + students.get_department_id() + ", " +
                "WHERE " + COLUMN_ID + "=" + Integer.valueOf(_id) + ";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);*/

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, students.get_name());
        values.put(COLUMN_PHONE_NO, students.get_phoneNo());
        values.put(COLUMN_REG_NO, students.get_regNo());
        values.put(COLUMN_EMAIL, students.get_email());
        values.put(COLUMN_BIRTHDAY, students.get_birthday());
        values.put(COLUMN_GENDER, students.get_gender());
        values.put(COLUMN_GENDER_ID, students.get_gender_id());
        values.put(COLUMN_DEPARTMENT, students.get_department());
        values.put(COLUMN_DEPARTMENT_ID, students.get_department_id());
        db.update(TABLE_STUDENTS, values, COLUMN_ID + "=" + _id, null);

        db.close();
    }

    public void deleteStudent(String studentName, int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_ID + "=\"" + _id + "\" AND " + COLUMN_NAME + "=\"" + studentName + "\";";
        db.execSQL(query);
        db.close();
    }

    /**
     * Retrieve all the students form database
     *
     * @return ArrayList of all student names
     */
    public ArrayList[] getStudentList() {
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<String> studentIDs = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_NAME + " FROM " + TABLE_STUDENTS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        Log.i(TAG, String.valueOf(c.getCount()));

        for (int i = 0; i < c.getCount(); i++) {
            c.moveToPosition(i);
            if (c.getString(c.getColumnIndex(COLUMN_ID)) != null && c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                Log.i(TAG, c.getString(c.getColumnIndex(COLUMN_NAME)));
                studentNames.add(c.getString(c.getColumnIndex(COLUMN_NAME)));
                studentIDs.add(c.getString(c.getColumnIndex(COLUMN_ID)));
            }
        }

        db.close();
        return new ArrayList[]{studentNames, studentIDs};
    }

    /**
     * Retrieve all information for details about a student(except gender id and department id)
     *
     * @param studentName Name of the student
     * @param _id         Database id of a specific student
     * @return Details of a student in String[]
     */
    public String[] getStudentDetails(String studentName, int _id) {
        String[] studentDetails = new String[8];
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_ID + "=\"" + _id + "\" AND " + COLUMN_NAME + "=\"" + studentName + "\";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if (c.getCount() == 1) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_REG_NO)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_PHONE_NO)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_EMAIL)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_BIRTHDAY)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_GENDER)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_DEPARTMENT)) != null) {
                studentDetails[0] = c.getString(c.getColumnIndex(COLUMN_NAME));
                studentDetails[1] = c.getString(c.getColumnIndex(COLUMN_GENDER));
                studentDetails[2] = c.getString(c.getColumnIndex(COLUMN_BIRTHDAY));
                studentDetails[3] = c.getString(c.getColumnIndex(COLUMN_DEPARTMENT));
                studentDetails[4] = c.getString(c.getColumnIndex(COLUMN_REG_NO));
                studentDetails[5] = c.getString(c.getColumnIndex(COLUMN_PHONE_NO));
                studentDetails[6] = c.getString(c.getColumnIndex(COLUMN_EMAIL));
                studentDetails[7] = c.getString(c.getColumnIndex(COLUMN_ID));
            }
        }
        db.close();
        return studentDetails;
    }

    /**
     * Retrieve all information for details about a student
     *
     * @param studentName name of the student
     * @param _id         Database id of a specific student
     * @return Details of a student in String[]
     */
    public String[] getStudentFullDetails(String studentName, int _id) {
        String[] studentDetails = new String[8];
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_ID + "=\"" + _id + "\" AND " + COLUMN_NAME + "=\"" + studentName + "\";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if (c.getCount() == 1) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_REG_NO)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_PHONE_NO)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_EMAIL)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_BIRTHDAY)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_GENDER_ID)) != null &&
                    c.getString(c.getColumnIndex(COLUMN_DEPARTMENT_ID)) != null) {
                studentDetails[0] = c.getString(c.getColumnIndex(COLUMN_NAME));
                studentDetails[1] = c.getString(c.getColumnIndex(COLUMN_EMAIL));
                studentDetails[2] = c.getString(c.getColumnIndex(COLUMN_REG_NO));
                studentDetails[3] = c.getString(c.getColumnIndex(COLUMN_PHONE_NO));
                studentDetails[4] = c.getString(c.getColumnIndex(COLUMN_GENDER_ID));
                studentDetails[5] = c.getString(c.getColumnIndex(COLUMN_DEPARTMENT_ID));
                studentDetails[6] = c.getString(c.getColumnIndex(COLUMN_BIRTHDAY));
                studentDetails[7] = c.getString(c.getColumnIndex(COLUMN_ID));
            }
        }
        db.close();
        return studentDetails;
    }
}
