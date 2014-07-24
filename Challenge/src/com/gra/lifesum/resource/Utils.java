package com.gra.lifesum.resource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * <p>Util methods here</p>
 * @author GabiRadu
 *
 */
public class Utils {
	
	public static boolean hasNetworkConnection(Context context) {
	    ConnectivityManager connMgr = (ConnectivityManager) 
	    		context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    return (networkInfo != null && networkInfo.isConnected());
	}
}
