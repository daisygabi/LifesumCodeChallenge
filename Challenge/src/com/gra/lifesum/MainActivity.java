package com.gra.lifesum;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gra.lifesum.persistence.DatabaseManager;
import com.gra.lifesum.persistence.SearchTextWatcher;
import com.gra.lifesum.persistence.adapter.LocalFavoriteFoodAdapter;
import com.gra.lifesum.persistence.adapter.SearchAutocompleteAdapter;
import com.gra.lifesum.persistence.domain.FoodItem;

/**
 * <p>Main class for the application</p>
 * User can search,save food items to favorite list and delete items from that list.

 * @author GabiRadu
 *
 */
public class MainActivity extends ActionBarActivity {

	private LocalFavoriteFoodAdapter adapter;
	private AutoCompleteTextView autocompleteTxtView;
	private ListView lView;
	private ImageView noDataImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// init local database
		DatabaseManager.init(this);

		adapter = new LocalFavoriteFoodAdapter(new ArrayList<FoodItem>(), this);

		// GUI
		lView = (ListView) findViewById(R.id.list_view);
		noDataImg = (ImageView) findViewById(R.id.noDataImg);
		autocompleteTxtView = (AutoCompleteTextView) findViewById(R.id.foodAutocompleteTxt);

		final SearchAutocompleteAdapter autocompleteAdapter = new SearchAutocompleteAdapter(
				this, R.layout.autocomplete_item);
		autocompleteAdapter.setNotifyOnChange(true);

		// The items will be fetched from the API in the textChangeListener
		autocompleteTxtView.addTextChangedListener(new SearchTextWatcher(
				autocompleteTxtView, autocompleteAdapter, MainActivity.this));
		autocompleteTxtView
				.setOnItemClickListener(new MyAutoCompleteItemClickListener());
		autocompleteTxtView.setAdapter(autocompleteAdapter);
		autocompleteTxtView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				String text = autocompleteTxtView.getText().toString();
				adapter.filterFavourites(text, DatabaseManager
						.getInstance().getFoodItems());
				
				noDataImg.setVisibility(View.GONE);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});

		lView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {
//				String selectedItem = adapter.getItemAtPosition(position)
//						.toString();
//				System.out.println("selected ittem: " + selectedItem);
			}
		});
		
		/**
		 * Show existing favorite food if existing
		 */
		showFavouriteFoodItems(lView, noDataImg);
	}

	public class MyAutoCompleteItemClickListener implements
			AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long l) {
			FoodItem clicked = ((SearchAutocompleteAdapter) adapterView
					.getAdapter()).getItem(position);
			System.out.println("you chose: " + clicked);
			createNewFoodItem(clicked.getTitle(), clicked.getCategory());
			autocompleteTxtView.dismissDropDown();
			autocompleteTxtView.setText("");
		}
	}

	private void showFavouriteFoodItems(ListView lView, ImageView image) {
		final List<FoodItem> items = DatabaseManager.getInstance()
				.getFoodItems();
		List<String> titles = new ArrayList<String>();

		for (FoodItem item : items) {
			titles.add(item.getTitle());
		}

		adapter.setItemList(items);
		adapter.notifyDataSetChanged();
		lView.setAdapter(adapter);

		if (!titles.isEmpty()) {
			lView.setVisibility(View.VISIBLE);
			image.setVisibility(View.GONE);
		}
	}

	private void createNewFoodItem(String title, String category) {
		FoodItem item = new FoodItem();
		item.setTitle(title);
		item.setCategory(category);
		DatabaseManager.getInstance().newFoodItem(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_clear_fav:
			DatabaseManager.getInstance().deleteAll();
			adapter.notifyDataSetChanged();
			lView.setAdapter(null);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}