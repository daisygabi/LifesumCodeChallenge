package com.gra.lifesum.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * <p>JsonRequest with additional header namepairvalues</p>
 * @author GabiRadu
 *
 */
public class LifesumRequest extends JsonObjectRequest {

	public LifesumRequest(String url, Listener<JSONObject> listener,
			ErrorListener errorListener, List<NameValuePair> params) {
		super(url, null, listener, errorListener);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization",
				"a794ecd348a3f71894426c65c37fea35da89a295bcbad687ca68a96fbfc7d371");
		return headers;
	}
}