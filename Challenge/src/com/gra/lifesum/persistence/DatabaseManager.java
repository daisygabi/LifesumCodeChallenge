package com.gra.lifesum.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.gra.lifesum.persistence.domain.FoodItem;

/**
 * <p>Logic for database operations</p>
 * @author GabiRadu
 *
 */
public class DatabaseManager {

	static private DatabaseManager instance;

	static public void init(Context ctx) {
		if (null == instance) {
			instance = new DatabaseManager(ctx);
		}
	}

	static public DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseHelper helper;

	private DatabaseManager(Context ctx) {
		helper = new DatabaseHelper(ctx);
	}

	private DatabaseHelper getHelper() {
		return helper;
	}

	/**
	 * Food item operations
	 * 
	 * @param foodItemId
	 * @return
	 */
	public FoodItem getFoodItemWithId(int foodItemId) {
		FoodItem items = null;
		try {
			items = getHelper().getFoodItemDao().queryForId(foodItemId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	public FoodItem newFoodItem() {
		FoodItem foodItem = new FoodItem();
		try {
			// persist the account object to the database
			// it should return 1 for the 1 row inserted
			getHelper().getFoodItemDao().create(foodItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodItem;
	}

	public void newFoodItem(FoodItem foodItem) {
		try {
			getHelper().getFoodItemDao().create(foodItem);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
		}
	}

	public List<FoodItem> getFoodItems() {
		List<FoodItem> items = new ArrayList<FoodItem>();
		try {
			items = getHelper().getFoodItemDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
		}
		return items;
	}

	public void deleteAll() {
		try {
			getHelper().getFoodItemDao().delete(getFoodItems());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
		}
	}

	public void deleteFoodItem(FoodItem foodItem) {
		try {
			getHelper().getFoodItemDao().delete(foodItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateFoodItem(FoodItem foodItem) {
		try {
			getHelper().getFoodItemDao().update(foodItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void refreshFoodList(FoodItem foodItem) {
		try {
			getHelper().getFoodItemDao().refresh(foodItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}