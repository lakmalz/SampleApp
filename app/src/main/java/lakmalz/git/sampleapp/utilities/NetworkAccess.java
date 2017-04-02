package lakmalz.git.sampleapp.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import lakmalz.git.sampleapp.R;


/**
 * Created by Lakmal Weerasekara on 11/1/17.
 */

public class NetworkAccess {
    private static Context mContext;

    public NetworkAccess(Context context) {
        mContext = context;
    }

    public static boolean isNetworkAvailable(final Context context) {
        mContext = context;
        boolean state = isInternetAvailable();
        if (!state) {
            Toast.makeText(mContext, R.string.no_internet, Toast.LENGTH_LONG).show();
        }
        return state;
    }

    private static boolean isInternetAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
