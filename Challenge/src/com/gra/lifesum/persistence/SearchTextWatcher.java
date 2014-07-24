package com.gra.lifesum.persistence;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gra.lifesum.persistence.adapter.SearchAutocompleteAdapter;
import com.gra.lifesum.persistence.domain.FoodItem;

/**
 * <p>Listener for autocomplete text view.\n
 * Create a delay before making an API request, because it can cause too many requests and data/batery consumption.</p>
 */
public class SearchTextWatcher implements TextWatcher {
	
	private static final int AUTOCOMPLETION_DELAY = 300; // in milliseconds
	private final CountDownTimer mTimer;
	private final String url = "https://api.lifesum.com/v1/search/query?type=food&search=";
	
	private final AutoCompleteTextView mAutocompleteTxtView;
	private final SearchAutocompleteAdapter mAdapter;
	private CharSequence mInputSearch;
	
	private Activity mActivity;
	private List<FoodItem> foundItems = new ArrayList<FoodItem>();

	public SearchTextWatcher(AutoCompleteTextView autoCompleteTextView,
			SearchAutocompleteAdapter autoCompleteAdapter, Activity activity) {
		mAutocompleteTxtView = autoCompleteTextView;
		mAdapter = autoCompleteAdapter;
		mActivity = activity;
		mTimer = getAutoCompletionTimer();
	}

	private CountDownTimer getAutoCompletionTimer() {
		return new CountDownTimer(AUTOCOMPLETION_DELAY, AUTOCOMPLETION_DELAY) {
			@Override
			public void onTick(long l) {
			}

			@Override
			public void onFinish() {
				if (mInputSearch.length() > 0) {
					
					// call search api
					callLifesumSearchApi(mActivity);

					if (foundItems != null && !foundItems.isEmpty()) {
						for (FoodItem result : foundItems) {
							mAdapter.add(result);
						}
					}
				}
			}
		};
	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i1,
			int i2) {
	}

	@Override
	public void onTextChanged(CharSequence currentSearchString, int start,
			int before, int count) {
		if (currentSearchString.length() < mAutocompleteTxtView.getThreshold()) {
			cancelCountDownTimer();
			if (!mAdapter.isEmpty())
				mAdapter.clear();
			return;
		}

		mInputSearch = currentSearchString;
		resetCountDownTimer();
	}

	@Override
	public void afterTextChanged(Editable editable) {
	}

	private void resetCountDownTimer() {
		cancelCountDownTimer();
		mTimer.start();
	}

	private void cancelCountDownTimer() {
		mTimer.cancel();
	}

	private void callLifesumSearchApi(Activity mActivity) {
		final List<FoodItem> items = new ArrayList<FoodItem>();
		RequestQueue queue = Volley.newRequestQueue(mActivity);
		
		// prepare the Request. Using Volley
		LifesumRequest getRequest = new LifesumRequest(url + "'"
				+ mAutocompleteTxtView.getText().toString().trim() + "'",
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							JSONObject json = response
									.getJSONObject("response");
							JSONArray responseList = json.getJSONArray("list");
							for (int i = 0; i < responseList.length(); i++) {
								FoodItem item = new FoodItem(responseList
										.getJSONObject(i));
								items.add(item);
							}

							// clear list before adding the results
							mAdapter.clear();
							
							for (FoodItem item : items) {
								foundItems.add(item);
								mAdapter.add(item);
							}
						} catch (JSONException e) {
							System.out.println("jsonexception: "
									+ e.getMessage());
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("Error.Response", error.getMessage());
					}
				}, null);

		// add it to the RequestQueue
		queue.add(getRequest);
	}
}