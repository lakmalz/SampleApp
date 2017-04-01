package lakmalz.git.sampleapp.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

import lakmalz.git.sampleapp.R;


/**
 * Created by Lakmal Weerasekara on 11/1/17.
 */

public class Utilities {

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public final static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }


    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }


    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static void showSnackBar(final Context context, final View mRootView, String text, String buttonText) {

        Snackbar mSnackbar = Snackbar.make(mRootView, text, Snackbar.LENGTH_LONG);

        if (!mSnackbar.isShown()) {
            mSnackbar = Snackbar.make(mRootView, text, Snackbar.LENGTH_LONG);
            //set te action button text color
            mSnackbar.setActionTextColor(ActivityCompat.getColor(mRootView.getContext(), R.color.colorPrimaryDark));
            //Get the view of the snackbar
            mSnackbar.setAction(buttonText, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + mRootView.getContext().getPackageName()));
                    myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                    myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(myAppSettings);
                }
            });
            mSnackbar.show();
        }
    }

    public static Bitmap rotateImage(Bitmap bitmap, String _filePath, Context context) {

        ExifInterface ei = null;
        try {
            ei = new ExifInterface(_filePath);
        } catch (IOException e) {
            Toast.makeText(context, "File not found.", Toast.LENGTH_LONG).show();
            return null;
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        int angle = 0;
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                angle = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                angle = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                angle = 270;
                break;
            case ExifInterface.ORIENTATION_NORMAL:
                angle = 0;
            default:
                break;
        }

        if (angle != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                    true);
        } else {
            return bitmap;
        }

    }

    public static String getStringCountMoreThanHundred(int count) {
        if (count >= 100)
            return "99+";
        else
            return count + "";

    }

    /*private void loadImageByUrl(final String url, final OkHttpClient okHttpClient, ImageView ) {
        imageView.setMaxScale(5.0f);
        //final Picasso picasso = Picasso.with(mActivity);

        *//*imageView.setBitmapDecoderFactory(new DecoderFactory<ImageDecoder>() {
            @Override
            public ImageDecoder make() throws IllegalAccessException, java.lang.InstantiationException {
                Log.e("FM", "1");
                return new PicassoDecoder(url, picasso);
            }
        });*//*

        imageView.setRegionDecoderFactory(new DecoderFactory<ImageRegionDecoder>() {
            @Override
            public ImageRegionDecoder make() throws IllegalAccessException, InstantiationException {
                //pb_zoom_pan.setVisibility(View.GONE);
                return new PicassoRegionDecoder(okHttpClient, mCallback);
            }

        });

        imageView.setOnImageEventListener(new SubsamplingScaleImageView.DefaultOnImageEventListener());
        imageView.setImage(ImageSource.uri(url));
    }*/
}
