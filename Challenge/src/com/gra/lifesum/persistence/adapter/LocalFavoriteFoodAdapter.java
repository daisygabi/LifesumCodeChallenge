package com.gra.lifesum.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gra.lifesum.R;
import com.gra.lifesum.persistence.DatabaseManager;
import com.gra.lifesum.persistence.domain.FoodItem;

/**
 * <p>
 * Adapter for showing favorite food items
 * </p>
 * 
 * @author gabrielaradu
 * 
 */
public class LocalFavoriteFoodAdapter extends ArrayAdapter<FoodItem> implements
		Filterable {

	private List<FoodItem> itemList;
	private Activity context;
	private ArrayList<FoodItem> arraylist;

	public LocalFavoriteFoodAdapter(List<FoodItem> items, Activity ctx) {
		super(ctx, android.R.layout.simple_list_item_1, items);
		setItemList(items);
		this.itemList = getItemList();
		this.context = ctx;
		this.arraylist = new ArrayList<FoodItem>();
		this.arraylist.addAll(items);
	}

	public int getCount() {
		if (itemList != null)
			return itemList.size();
		return 0;
	}

	public FoodItem getItem(int position) {
		if (itemList != null)
			return itemList.get(position);
		return null;
	}

	public long getItemId(int position) {
		if (itemList != null)
			return itemList.get(position).hashCode();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row, null);
		}

		final FoodItem c = itemList.get(position);

		TextView nameTxt = (TextView) v.findViewById(R.id.name);
		nameTxt.setText(c.getTitle());

		TextView itemLocalDetailsTxt = (TextView) v
				.findViewById(R.id.itemLocalDetails);
		itemLocalDetailsTxt.setText(c.getCategory() + " (Fiber: "
				+ c.getFiber() + ", Protein: " + c.getProtein() + ")");

		ImageView deleteImg = (ImageView) v.findViewById(R.id.deleteItem);

		// add listener to row in order to delete the
		deleteImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DatabaseManager.init(context);
				DatabaseManager.getInstance().deleteFoodItem(c);
				Toast.makeText(context, "Deleted from favorites.",
						Toast.LENGTH_SHORT).show();

				notifyDataSetChanged();
			}
		});

		return v;
	}

	public List<FoodItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<FoodItem> itemList) {
		this.itemList = itemList;
	}

	public int filterFavourites(String charText, List<FoodItem> arraylist) {
		charText = charText.toLowerCase(Locale.getDefault());
		if (arraylist.isEmpty()) {
			this.arraylist.addAll(itemList);
		}
		itemList.clear();

		if (charText.length() == 0) {
			itemList.addAll(arraylist);
		} else {
			for (FoodItem item : arraylist) {
				if (item.getTitle().toLowerCase(Locale.getDefault())
						.contains(charText)
						|| (item.getCategory().toLowerCase(Locale.getDefault())
								.contains(charText))) {
					itemList.add(item);
				}
			}
		}
		notifyDataSetChanged();
		return itemList.size();
	}

}