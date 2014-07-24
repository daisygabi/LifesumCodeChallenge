package com.gra.lifesum.persistence;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gra.lifesum.persistence.domain.FoodItem;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	// name of the database file
	private static final String DATABASE_NAME = "lifesumfood.sqlite";

	// first 'commit' for  database version
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the FoodItem table
	private Dao<FoodItem, Integer> foodItemDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, FoodItem.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			List<String> allSql = new ArrayList<String>();
			switch (oldVersion) {
			case 1:
				// allSql.add("alter table AdData add column `new_col` VARCHAR");
				// allSql.add("alter table AdData add column `new_col2` VARCHAR");
			}
			for (String sql : allSql) {
				db.execSQL(sql);
			}
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Exception during onUpgrade",
					e);
			throw new RuntimeException(e);
		}

	}

	public Dao<FoodItem, Integer> getFoodItemDao() {
		if (null == foodItemDao) {
			try {
				foodItemDao = getDao(FoodItem.class);
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return foodItemDao;
	}

}
