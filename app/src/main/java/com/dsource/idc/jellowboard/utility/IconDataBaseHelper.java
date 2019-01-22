package com.dsource.idc.jellowboard.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.dsource.idc.jellowboard.R;

import java.util.ArrayList;

/**
 * Created by Ayaz Alam on 31/05/2018.
 */
public class IconDataBaseHelper extends SQLiteOpenHelper {

    Context context;
    // Declaring all these as constants makes code a lot more readable, and looking like SQL.
    // Versions has to be 1 first time or app will crash.
    private static final int DATABASE_VERSION = 1;
    private static final String ICON_LIST_TABLE = "icons";
    private static final String DATABASE_NAME = "level3.db";
    // Column names...
    private static final String ICON_ID = "_id";//Icon Primary key ID
    private static final String ICON_TITLE = "icon_title";//Icon Title
    private static final String ICON_DRAWABLE = "icon_drawable";//Icon Drawable
    private static final String KEY_P1 = "icon_p1";//First Level Parent
    private static final String KEY_P2 = "icon_p2";//Second Level Parent
    private static final String KEY_P3 = "icon_p3";//Third Level Parent
    // ... and a string array of columns.
    private static final String[] COLUMNS =
            {ICON_ID, ICON_TITLE,ICON_DRAWABLE,KEY_P1,KEY_P2,KEY_P3};
    // Build the SQL query that creates the table.
    private static final String ICON_LIST_TABLE_CREATE =
            "CREATE TABLE " + ICON_LIST_TABLE + " (" + ICON_ID + " INTEGER PRIMARY KEY, "
                    + ICON_TITLE + " TEXT," //Icon Title
                    + ICON_DRAWABLE + " TEXT,"//Icon's Drawable resource
                    + KEY_P1 + " INTERGER,"//Level 1 parent
                    + KEY_P2 + " INTEGER, " //Level 2 parent
                    + KEY_P3 +" INTEGER  );";//Level 3 parent
    private SQLiteDatabase mReadableDB;
    public IconDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }


    public void createTable(SQLiteDatabase db) {
        db.execSQL(ICON_LIST_TABLE_CREATE);
        //Fill the Table if the table is not created yet.
        fillDatabaseWithData(db,context);
    }

    /**
     * Adds the initial data set to the database.
     * According to the docs, onCreate for the open helper does not run on the UI thread.
     * @param db Database to fill with data since the member variables are not initialized yet.
     */
    public void fillDatabaseWithData(SQLiteDatabase db,Context context) {
        // Create a container for the data.
        ContentValues values = new ContentValues();
        //Filling the database for level 1 JellowIcon
        String[] levelOneIcon = context.getResources().getStringArray(R.array.arrLevelOneIconAdapter);
        String[] levelOneTitles = context.getResources().getStringArray(R.array.arrLevelOneActionBarTitle);
        //Level 1 JellowIcon
        for (int i = 0; i < levelOneIcon.length; i++) {
            // Put column/value pairs into the container. put() overwrites existing values.
            values.put(ICON_TITLE, levelOneTitles[i]);
            values.put(ICON_DRAWABLE, levelOneIcon[i]);
            values.put(KEY_P1, i);
            values.put(KEY_P2, -1);
            values.put(KEY_P3, -1);
            db.insert(ICON_LIST_TABLE, null, values);
        }
        //Filling the database for Level 2
        for (int i = 0; i < levelOneIcon.length; i++) {
            String[] levelTwoIcons = getIconLevel2(i);
            String[] levelTwoTitles = getIconTitleLevel2(i);
            for (int j = 0; j < levelTwoIcons.length; j++) {
                // Put column/value pairs into the container. put() overwrites existing values.
                values.put(ICON_TITLE, levelTwoTitles[j]);
                values.put(ICON_DRAWABLE, levelTwoIcons[j]);
                values.put(KEY_P1, i);
                values.put(KEY_P2, j);
                values.put(KEY_P3, -1);
                db.insert(ICON_LIST_TABLE, null, values);
            }
        }
        //Filling the database for Level Three
        for (int i = 0; i < levelOneIcon.length; i++) {
            String[] levelTwoIcons = getIconLevel2(i);
            for (int j = 0; j < levelTwoIcons.length; j++) {
                noChildInThird = false;
                if (loadArraysFromResources(i, j)) {
                    if (thirdLevelTitles != null) {
                        String[] levelThreeIcons = thirdLevelIcons;
                        String[] levelThreeTitle = thirdLevelTitles;
                        for (int k = 0; k < thirdLevelTitles.length; k++) {
                            // Put column/value pairs into the container. put() overwrites existing values.
                            if (noChildInThird)
                                break;
                            values.put(ICON_TITLE, levelThreeTitle[k]);
                            values.put(ICON_DRAWABLE, levelThreeIcons[k]);
                            values.put(KEY_P1, i);
                            values.put(KEY_P2, j);
                            values.put(KEY_P3, k);
                            db.insert(ICON_LIST_TABLE, null, values);
                        }

                    }
                }

            }
        }
        //Reset the language code
        new SessionManager(context).setLanguageChange(2);
    }

    /**
     * A function to return array of Icon of level two
     *
     * */

    private String[] getIconLevel2(int pos)
    {
        String arr[]=null;
     switch (pos)
     {
         case 0:arr=context.getResources().getStringArray(R.array.arrLevelTwoGreetFeelIconAdapter);
             break;
         case 1:arr=context.getResources().getStringArray(R.array.arrLevelTwoDailyActIconAdapter);
             break;
         case 2:arr=context.getResources().getStringArray(R.array.arrLevelTwoEatingIconAdapter);
             break;
         case 3:arr=context.getResources().getStringArray(R.array.arrLevelTwoFunIconAdapter);
             break;
         case 4:arr=context.getResources().getStringArray(R.array.arrLevelTwoLearningIconAdapter);
             break;
         case 5:arr=context.getResources().getStringArray(R.array.arrLevelTwoPeopleIcon);
             break;
         case 6:arr=context.getResources().getStringArray(R.array.arrLevelTwoPlacesIcon);
             break;
         case 7:arr=context.getResources().getStringArray(R.array.arrLevelTwoTimeIconAdapter);
             break;
         case 8:arr=context.getResources().getStringArray(R.array.arrLevelTwoHelpIconAdapter);
             break;
         default:
     }
     return arr;
    }

    /**
     * A function to return array of Titles of level 2
     *
     * */
    private String[] getIconTitleLevel2(int pos)
    {
        String arr[]=null;
        switch (pos)
        {
            case 0:arr=context.getResources().getStringArray(R.array.arrLevelTwoGreetFeelAdapterText);
                break;
            case 1:arr=context.getResources().getStringArray(R.array.arrLevelTwoDailyActAdapterText);
                break;
            case 2:arr=context.getResources().getStringArray(R.array.arrLevelTwoEatAdapterText);
                break;
            case 3:arr=context.getResources().getStringArray(R.array.arrLevelTwoFunAdapterText);
                break;
            case 4:arr=context.getResources().getStringArray(R.array.arrLevelTwoLearningAdapterText);
                break;
            case 5:arr=context.getResources().getStringArray(R.array.arrLevelTwoPeopleAdapterText);
                break;
            case 6:arr=context.getResources().getStringArray(R.array.arrLevelTwoPlacesAdapterText);
                break;
            case 7:arr=context.getResources().getStringArray(R.array.arrLevelTwoTimeWeatherAdapterText);
                break;
            case 8:arr=context.getResources().getStringArray(R.array.arrLevelTwoHelpAdapterText);
                break;
            default:
        }
        return arr;
    }

    /**
     * A function to return list of icon matching with query
     * Queries the database for an entry at a given position.
     *
     * @param iconTitle The Nth row in the table.
     * @return a JellowIcon class object ArrayList with the requested database entry.
     */
    @Nullable
    public ArrayList<JellowIcon> query(String iconTitle)
    {
        String selectQuery = "SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+ICON_TITLE+" LIKE '" + iconTitle + "%' LIMIT 20";
        Cursor cursor = null;
        JellowIcon thisIcon=null;
        ArrayList<JellowIcon> iconList=new ArrayList<>();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                iconList.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                        iconList.add(thisIcon);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
            cursor.close();
            return iconList;
        }
    }
    /**
     * A function to return list of icon matching with query
     * Queries the database for an entry at a given position.
     *
     * @param level_0 The Nth row in the table.
     * @return a JellowIcon class object ArrayList with the requested database entry.
     */
    @Nullable
    public JellowIcon fetchIcon(int level_0,int level_1,int level_3)
    {
        String selectQuery;
        selectQuery="SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+KEY_P1+" = " + level_0 + " AND "+KEY_P2+" = "+level_1+ " AND "+KEY_P3+" = "+level_3;
        Cursor cursor = null;
        JellowIcon thisIcon=null;
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();

                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                    }

            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
                cursor.close();
            return thisIcon;
        }
    }
    /**
     * A function to return list of icon matching with query
     * Queries the database for an entry at a given position.
     *
     * @param level_0 The Nth row in the table.
     * @return a JellowIcon class object ArrayList with the requested database entry.
     */
    @Nullable
    public ArrayList<JellowIcon> myBoardQuery(int level_0,int level_1)
    {
        String selectQuery;
        if(level_1==-1)
            selectQuery= "SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+KEY_P1+" = " + level_0 + "";
        else
            selectQuery="SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+KEY_P1+" = " + level_0 + " AND "+KEY_P2+" = "+level_1;
        Cursor cursor = null;
        JellowIcon thisIcon=null;
        ArrayList<JellowIcon> iconList=new ArrayList<>();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                iconList.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                        iconList.add(thisIcon);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
                cursor.close();
            return iconList;
        }
    }

    @Nullable
    public ArrayList<JellowIcon> getAllIcons()
    {
        String selectQuery = "SELECT * FROM "+ICON_LIST_TABLE +"";

        Cursor cursor = null;
        JellowIcon thisIcon=null;
        ArrayList<JellowIcon> iconList=new ArrayList<>();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                iconList.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                        iconList.add(thisIcon);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
                cursor.close();
            return iconList;
        }
    }

    @Nullable
    public ArrayList<JellowIcon> getLevelOneIcons()
    {
        String selectQuery = "SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+KEY_P2+" = -1 "+" AND "+KEY_P3 +" = -1 ";

        Cursor cursor = null;
        JellowIcon thisIcon=null;
        ArrayList<JellowIcon> iconList=new ArrayList<>();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                iconList.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                        iconList.add(thisIcon);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
                cursor.close();
            return iconList;
        }
    }
    @Nullable
    public ArrayList<JellowIcon> getLevelTwoIcons()
    {
        String selectQuery = "SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+KEY_P3 +" = -1 "+" AND "+KEY_P2 +" != -1 ";

        Cursor cursor = null;
        JellowIcon thisIcon=null;
        ArrayList<JellowIcon> iconList=new ArrayList<>();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                iconList.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                        iconList.add(thisIcon);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
                cursor.close();
            return iconList;
        }
    }
    @Nullable
    public ArrayList<JellowIcon> getLevelThreeIcons()
    {
        String selectQuery = "SELECT * FROM "+ICON_LIST_TABLE +" WHERE "+KEY_P1 +" != -1 "+" AND "+KEY_P2 +" != -1 "+" AND "+KEY_P3 +" != -1 ";

        Cursor cursor = null;
        JellowIcon thisIcon=null;
        ArrayList<JellowIcon> iconList=new ArrayList<>();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(selectQuery, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                iconList.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    if (cursor != null) {
                        String iconName = cursor.getString(cursor.getColumnIndex(ICON_TITLE));
                        String icon_drawable = cursor.getString(cursor.getColumnIndex(ICON_DRAWABLE));
                        int iconP1 = cursor.getInt(cursor.getColumnIndex(KEY_P1));
                        int iconP2 = cursor.getInt(cursor.getColumnIndex(KEY_P2));
                        int iconP3 = cursor.getInt(cursor.getColumnIndex(KEY_P3));
                        thisIcon = new JellowIcon(iconName, icon_drawable, iconP1, iconP2, iconP3);
                        iconList.add(thisIcon);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            //Catch Query Exception
        } finally {
            // Must close cursor and db now that we are done with it.
            if (cursor!=null)
                cursor.close();
            return iconList;
        }
    }

    /**
     *  Function to Drop Table
     * */
    public void dropTable(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + ICON_LIST_TABLE);
    }

    /**
     * Gets the number of rows in the icon list table.
     * @return The number of entries in ICON_LIST_TABLE.
     */
    public long count()
    {
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(mReadableDB, ICON_LIST_TABLE);
    }

    /**
     * Called when a database needs to be upgraded. The most basic version of this method drops
     * the tables, and then recreates them. All data is lost, which is why for a production app,
     * you want to back up your data first. If this method fails, changes are rolled back.
     * @param db
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + ICON_LIST_TABLE);
        onCreate(db);
    }

    boolean noChildInThird =false;
    private boolean loadArraysFromResources(int levelOneItemPos, int levelTwoItemPos)
    {
        if (levelOneItemPos == 0) {
            switch(levelTwoItemPos){
                case 0: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelGreetingIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelGreetingAdapterText));
                    break;
                case 1: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelFeelingIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelFeelingsAdapterText));
                    break;
                case 2: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelRequestsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelRequestsAdapterText));
                    break;
                case 3: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelQuestionsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeGreetFeelQuestionsAdapterText));
                    break;
            }
        } else if (levelOneItemPos == 1) {
            switch(levelTwoItemPos){
                case 0:
                    noChildInThird =true;break;
                case 1:
                    noChildInThird =true;break;
                case 2:
                    noChildInThird =true;break;
                case 3: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeDailyActClothesIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeDailyActClothesAccAdapterText));
                    break;
                case 4: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeDailyActGetReadyIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeDailyActGetReadyAdapterText));
                    break;
                case 5: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeDailyActSleepIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeDailyActSleepAdapterText));
                    break;
                case 6: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeDailyActTherapyIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeDailyActTherapyAdapterText));
                    break;
                case 7:
                    noChildInThird =true;break;
                case 8:
                    noChildInThird =true;break;
            }
        } else if (levelOneItemPos == 2) {
            switch(levelTwoItemPos) {
                case 0: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksBreakfastIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksBreakfastAdapterText));
                    break;
                case 1: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksLunchDinIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksLunchDinnerAdapterText));
                    break;
                case 2: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksSweetsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksSweetsAdapterText));
                    break;
                case 3: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksSnacksIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksSnacksAdapterText));
                    break;
                case 4: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksFruitsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksFruitsAdapterText));
                    break;
                case 5: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksDrinksIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksDrinksAdapterText));
                    break;
                case 6: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksCutleryIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksCutleryAdapterText));
                    break;
                case 7: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksAddonsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFoodDrinksAddonAdapterText));
                    break;
            }
        } else if (levelOneItemPos == 3) {
            switch(levelTwoItemPos){
                case 0: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFunInDGamesIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFunInDGamesAdapterText));
                    break;
                case 1: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFunOutDGamesIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFunOutDGamesAdapterText));
                    break;
                case 2: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFunSportsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFunSportsAdapterText));
                    break;
                case 3: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFunTvIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFunTvAdapterText));
                    break;
                case 4: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFunMusicIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFunMusicAdapterText));
                    break;
                case 5: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeFunActivitiesIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeFunActivitiesAdapterText));
                    break;
            }
        } else if (levelOneItemPos == 4) {
            switch(levelTwoItemPos) {
                case 0: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningAnimBirdsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningAnimBirdsAdapterText));
                    break;
                case 1: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningBodyPartsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningBodyPartsAdapterText));
                    break;
                case 2: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningBooksIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningBooksAdapterText));
                    break;
                case 3: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningColorsIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningColorsAdapterText));
                    break;
                case 4: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningShapesIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningShapesAdapterText));
                    break;
                case 5: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningStationaryIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningStationaryAdapterText));
                    break;
                case 6: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningSchoolIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningSchoolObjAdapterText));
                    break;
                case 7: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningHomeIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningHomeObjAdapterText));
                    break;
                case 8: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningTransportationIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningTransportAdapterText));
                    break;
                case 9: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeLearningMoneyIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeLearningMoneyAdapterText));
                    break;
            }
        }
        else if(levelOneItemPos==5)
        {
            noChildInThird =true;
        }
        else if(levelOneItemPos==6)
        {
            noChildInThird =true;
        }
        else if (levelOneItemPos == 7) {
            switch(levelTwoItemPos) {
                case 0: thirdLevelIcons=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaTimeIconAdapter);
                        thirdLevelTitles=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaTimeAdapterText);
                    break;
                case 1: thirdLevelIcons=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaDayIconAdapter);
                        thirdLevelTitles=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaDayAdapterText);
                    break;
                case 2: thirdLevelIcons=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaMonthIconAdapter);
                        thirdLevelTitles=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaMonthAdapterText);
                    break;
                case 3: thirdLevelIcons=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaWeatherIconAdapter);
                        thirdLevelTitles=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaWeatherAdapterText);
                    break;
                case 4: thirdLevelIcons=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaSeasonsIconAdapter);
                        thirdLevelTitles=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaSeasonsAdapterText);
                    break;
                case 5: thirdLevelIcons=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaHoliFestIconAdapter);
                        thirdLevelTitles=context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaHoliFestAdapterText);
                    break;
                case 6: loadAdapterMenuTextIconsWithoutSort(context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaBirthdaysIconAdapter),
                        context.getResources().getStringArray(R.array.arrLevelThreeTimeWeaBirthdaysAdapterText));
                    break;
            }
        }
        else if(levelOneItemPos==8)
        {
            noChildInThird =true;
        }

        return true;
    }//End of the function

    String[] thirdLevelIcons=null;
    String[] thirdLevelTitles=null;
    private void loadAdapterMenuTextIconsWithoutSort(String[] typeIconArray, String[] stringBelowTextArray)
    {
    thirdLevelIcons=typeIconArray;
    thirdLevelTitles=stringBelowTextArray;
    }

}

//END