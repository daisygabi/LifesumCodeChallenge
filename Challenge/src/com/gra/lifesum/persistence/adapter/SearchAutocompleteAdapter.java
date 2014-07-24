package com.gra.lifesum.persistence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.gra.lifesum.R;
import com.gra.lifesum.persistence.DatabaseManager;
import com.gra.lifesum.persistence.domain.FoodItem;

/**
 * <p>Adapter for showing found items calling search api endpoint.</p>
 */
public class SearchAutocompleteAdapter extends ArrayAdapter<FoodItem> implements
		Filterable {
	private int mLayoutResourceID;

	public SearchAutocompleteAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		mLayoutResourceID = textViewResourceId;
		DatabaseManager.init(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					mLayoutResourceID, parent, false);
		}

		final FoodItem item = getItem(position);
		((TextView) (convertView.findViewById(R.id.autocomplete_item)))
				.setText(item.getTitle());
		((TextView) (convertView.findViewById(R.id.autocomplete_category)))
				.setText(item.getCategory());
		((TextView) (convertView.findViewById(R.id.autocomplete_details)))
				.setText(item.getCalories() + " Kal");

		return convertView;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	private Filter filter = new Filter() {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			if (constraint != null)
				filterResults.count = getCount();
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence contraint,
				FilterResults results) {
			if (results != null && results.count > 0) {
				notifyDataSetChanged();
			}
		}
	};
}
