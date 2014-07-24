package com.gra.lifesum.persistence.domain;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * <p>
 * POJO class to describe a Food item
 * </p>
 * 
 * @author GabiRadu
 * 
 */
@DatabaseTable
public class FoodItem {
	
	private final String TAG = FoodItem.class.getName();

	@DatabaseField(generatedId=true)
	private int localId;
	
	@DatabaseField(useGetSet = true)
	private String headImage;
	
	@DatabaseField(useGetSet = true)
	private int potassium;
	
	@DatabaseField(useGetSet = true)
	private String title;
	
	@DatabaseField(useGetSet = true)
	private int sodium;
	
	@DatabaseField(useGetSet = true)
	private int gramsPerServing;

	@DatabaseField(useGetSet = true)
	private int servingCategory;
	
	@DatabaseField(useGetSet = true)
	private String brand;
	
	@DatabaseField(useGetSet = true)
	private int defaultServing;

	@DatabaseField(useGetSet = true)
	private int carbohydrates;
	
	@DatabaseField(useGetSet = true)
	private String category;
	
	@DatabaseField(useGetSet = true)
	private double calories;
	
	@DatabaseField(useGetSet = true)
	private int fat;
	
	@DatabaseField(useGetSet = true)
	private int unsaturatedFat;
	
	@DatabaseField(useGetSet = true)
	private Double sugar; // come back to this
	
	@DatabaseField(useGetSet = true)
	private int fiber;

	@DatabaseField
	private boolean verified;
	
	@DatabaseField(useGetSet = true)
	private String pcstext;
	
	@DatabaseField(useGetSet = true)
	private int mlingram;
	
	@DatabaseField(useGetSet = true)
	private double id;
	
	@DatabaseField(useGetSet = true)
	private int measeureId;
	
	@DatabaseField(useGetSet = true)
	private int pcsingram;
	
	@DatabaseField(useGetSet = true)
	private int protein;
	
	@DatabaseField(useGetSet = true)
	private int servingVersion;
	
	@DatabaseField(useGetSet = true)
	private int typeOfMeasurement;

	@DatabaseField(useGetSet = true)
	private int saturatedFat;
	
	@DatabaseField(useGetSet = true)
	private int showOnlySameType;
	
	@DatabaseField(useGetSet = true)
	private int showMeasurement;
	
	@DatabaseField(useGetSet = true)
	private int categoryId;
	
	@DatabaseField(useGetSet = true)
	private int colesterol;

	public FoodItem() {
	}

	/**
	 * Map a JSONObject to local Food class
	 * @param jsonObj
	 */
	public FoodItem(JSONObject jsonObj) {
		try {
			this.headImage = jsonObj.getString("headimage");
			this.potassium = jsonObj.getInt("potassium");
			this.title = jsonObj.getString("title");
			this.sodium = jsonObj.getInt("sodium");
			this.gramsPerServing = jsonObj.getInt("gramsperserving");
			this.servingCategory = jsonObj.getInt("servingcategory");
			this.brand = jsonObj.getString("brand");
			this.defaultServing = jsonObj.getInt("defaultserving");
			this.carbohydrates = jsonObj.getInt("carbohydrates");
			this.category = jsonObj.getString("category");
			this.calories = jsonObj.getDouble("calories");
			this.fat = jsonObj.getInt("fat");
			this.unsaturatedFat = jsonObj.getInt("unsaturatedfat");
			this.sugar = jsonObj.getDouble("sugar");
			this.fiber = jsonObj.getInt("fiber");

			this.verified = jsonObj.getBoolean("verified");
			this.pcstext = jsonObj.getString("pcstext");
			this.mlingram = jsonObj.getInt("mlingram");
			this.id = jsonObj.getDouble("id");
			this.measeureId = jsonObj.getInt("measurementid");

			this.pcsingram = jsonObj.getInt("pcsingram");
			this.protein = jsonObj.getInt("protein");
			this.servingVersion = jsonObj.getInt("serving_version");
			this.typeOfMeasurement = jsonObj.getInt("typeofmeasurement");

			this.saturatedFat = jsonObj.getInt("saturatedfat");
			this.showOnlySameType = jsonObj.getInt("showonlysametype");
			this.showMeasurement = jsonObj.getInt("showmeasurement");
			this.categoryId = jsonObj.getInt("categoryid");
			this.colesterol = jsonObj.getInt("cholesterol");

		} catch (JSONException e) {
			// add exception handling
			Log.d(TAG, "Error mapping the received JSONobject to the class. Error with: " + e.getMessage());
		}
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public int getPotassium() {
		return potassium;
	}

	public void setPotassium(int potassium) {
		this.potassium = potassium;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

	public int getGramsPerServing() {
		return gramsPerServing;
	}

	public void setGramsPerServing(int gramsPerServing) {
		this.gramsPerServing = gramsPerServing;
	}

	public int getServingCategory() {
		return servingCategory;
	}

	public void setServingCategory(int servingCategory) {
		this.servingCategory = servingCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getDefaultServing() {
		return defaultServing;
	}

	public void setDefaultServing(int defaultServing) {
		this.defaultServing = defaultServing;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getUnsaturatedFat() {
		return unsaturatedFat;
	}

	public void setUnsaturatedFat(int unsaturatedFat) {
		this.unsaturatedFat = unsaturatedFat;
	}

	public Double getSugar() {
		return sugar;
	}

	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}

	public int getFiber() {
		return fiber;
	}

	public void setFiber(int fiber) {
		this.fiber = fiber;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getPcstext() {
		return pcstext;
	}

	public void setPcstext(String pcstext) {
		this.pcstext = pcstext;
	}

	public int getMlingram() {
		return mlingram;
	}

	public void setMlingram(int mlingram) {
		this.mlingram = mlingram;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public int getMeaseureId() {
		return measeureId;
	}

	public void setMeaseureId(int measeureId) {
		this.measeureId = measeureId;
	}

	public int getPcsingram() {
		return pcsingram;
	}

	public void setPcsingram(int pcsingram) {
		this.pcsingram = pcsingram;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getServingVersion() {
		return servingVersion;
	}

	public void setServingVersion(int servingVersion) {
		this.servingVersion = servingVersion;
	}

	public int getTypeOfMeasurement() {
		return typeOfMeasurement;
	}

	public void setTypeOfMeasurement(int typeOfMeasurement) {
		this.typeOfMeasurement = typeOfMeasurement;
	}

	public int getSaturatedFat() {
		return saturatedFat;
	}

	public void setSaturatedFat(int saturatedFat) {
		this.saturatedFat = saturatedFat;
	}

	public int getShowOnlySameType() {
		return showOnlySameType;
	}

	public void setShowOnlySameType(int showOnlySameType) {
		this.showOnlySameType = showOnlySameType;
	}

	public int getShowMeasurement() {
		return showMeasurement;
	}

	public void setShowMeasurement(int showMeasurement) {
		this.showMeasurement = showMeasurement;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getColesterol() {
		return colesterol;
	}

	public void setColesterol(int colesterol) {
		this.colesterol = colesterol;
	}

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(title);
		return buffer.toString();
	}
}
