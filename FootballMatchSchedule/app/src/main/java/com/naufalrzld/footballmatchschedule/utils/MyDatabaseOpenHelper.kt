package com.naufalrzld.footballmatchschedule.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.naufalrzld.footballmatchschedule.model.MatchModel
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            MatchModel.TABLE_FAVORITE, true,
            MatchModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchModel.EVENT_ID to TEXT + UNIQUE,
            MatchModel.HOME_TEAM to TEXT,
            MatchModel.HOME_SCORE to TEXT,
            MatchModel.AWAY_TEAM to TEXT,
            MatchModel.AWAY_SCORE to TEXT,
            MatchModel.EVENT_DATE to TEXT,
            MatchModel.HOME_GOAL_KEEPER to TEXT,
            MatchModel.AWAY_GOAL_KEEPER to TEXT,
            MatchModel.HOME_DEFENSE to TEXT,
            MatchModel.AWAY_DEFENSE to TEXT,
            MatchModel.HOME_MIDFIELD to TEXT,
            MatchModel.AWAY_MIDFIELD to TEXT,
            MatchModel.HOME_FORWARD to TEXT,
            MatchModel.AWAY_FORWARD to TEXT,
            MatchModel.HOME_SUBSTITUTES to TEXT,
            MatchModel.AWAY_SUBSTITUTES to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchModel.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)