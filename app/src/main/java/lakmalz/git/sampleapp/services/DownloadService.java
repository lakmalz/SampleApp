package lakmalz.git.sampleapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.URLUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lakmal Weerasekara on 24/1/17.
 */

public class DownloadService /*extends IntentService*/ {

    /*public static final String ARG_FILE_ID = "fileId";
    public static final String ARG_FILE = "file";
    public static final String ARG_FILE_STATUS = "status";
    public static final String ARG_FILE_PROGRESS = "progress";
    public static final String ARG_FILE_ERROR = "error";

    public static final int DOWNLOAD_ERROR = 10;
    public static final int DOWNLOAD_SUCCESS = 11;
    public static final int DOWNLOAD_PROCESSING = 12;

    private String broadCaster;
    private boolean isRunning = false;
    private int totalSize;
    private int loadedSize;
    private volatile boolean killed = false;
    private volatile boolean running = true;
    public static List<Template> mTemplates;
    private Realm mRealm;

    public DownloadService() {
        super(ArtworkDownloadService.class.getName());
        isRunning = false;
        mTemplates = new ArrayList<Template>();
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        broadCaster = getResources().getString(R.string.service_artwork_download_broadcast);
        Template item = (Template) intent.getSerializableExtra(Constant.TEMPLATE_OBJECT);
        if(!checkInQueue(item))
        {
            mTemplates.add(item);
            downlaodNext();
        }
    }

    public static boolean checkInQueue(Template newTemplate)
    {
        boolean isInQueue = false;
        if (mTemplates != null) {
            for (Template item : mTemplates) {
                if (newTemplate.category_id == item.category_id) {
                    isInQueue = true;
                    break;
                }
            }
        }
        return isInQueue;
    }
    private void downlaodNext()
    {
        if(!isRunning && mTemplates.size() > 0)
            downloadFile(mTemplates.get(0));
    }

    private void downloadFile(Template template) {
        isRunning = true;

        try {
            URL toDownload = new URL(template.image_png_url);
            HttpURLConnection urlConnection = (HttpURLConnection) toDownload.openConnection();

            urlConnection.setRequestMethod("GET");

            urlConnection.connect();
            File sdCardRoot = Environment.getExternalStorageDirectory();
            sdCardRoot = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constant.FOLDERNAME);

            boolean success = true;

            if (!sdCardRoot.exists()) {
                success = sdCardRoot.mkdir();
            }
            if (success) {
                String fileName = URLUtil.guessFileName(toDownload.getFile(), null, null);
                File outFile = new File(sdCardRoot, fileName);
                FileOutputStream fileOutput = new FileOutputStream(outFile);

                InputStream inputStream = urlConnection.getInputStream();
                totalSize = urlConnection.getContentLength();
                loadedSize = 0;

                byte[] buffer = new byte[1024];
                int bufferLength = 0; //used to store a temporary size of the buffer

                while (!killed && (bufferLength = inputStream.read(buffer)) > 0) {
                    while (!running && !killed) {
                        Thread.sleep(500);
                    }
                    if (!killed) {

                        isRunning = true;

                        fileOutput.write(buffer, 0, bufferLength);
                        loadedSize += bufferLength;
                        double val = ((double) loadedSize / totalSize) * 100;

                        broadCastProgress(template, val);
                    }
                }

                fileOutput.close();
                if (killed && outFile.exists()) {
                    outFile.delete();
                }


                template.save_path = outFile.getAbsolutePath();
                broadcastCompleted(template, 100);

            }

        } catch (Exception e) {
            broadCastError(template, 0, e);
            e.printStackTrace();
        } finally {
            totalSize = 0;
            loadedSize = 0;

            // TODO: 24/1/17 uncomment
            mTemplates.remove(0);
            isRunning = false;
            downlaodNext();
        }
    }

    private void broadCastProgress(Template template, double percentage) {
        Intent broadcaster = new Intent();
        broadcaster.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        broadcaster.setAction(broadCaster);
        broadcaster.putExtra(ARG_FILE_STATUS, DOWNLOAD_PROCESSING);
        broadcaster.putExtra(ARG_FILE_PROGRESS, percentage);
        broadcaster.putExtra(ARG_FILE_ID, template.template_id);
        sendBroadcast(broadcaster);
    }

    private void broadcastCompleted(Template template, double percentage) {
        Intent broadcaster = new Intent();
        broadcaster.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        broadcaster.setAction(broadCaster);
        broadcaster.putExtra(ARG_FILE_STATUS, DOWNLOAD_SUCCESS);
        broadcaster.putExtra(ARG_FILE_PROGRESS, percentage);
        broadcaster.putExtra(ARG_FILE, template);
        sendBroadcast(broadcaster);
    }

    private void broadCastError(Template template, double percentage, Exception ex) {
        Intent broadcaster = new Intent();
        broadcaster.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        broadcaster.setAction(broadCaster);
        broadcaster.putExtra(ARG_FILE_STATUS, DOWNLOAD_ERROR);
        broadcaster.putExtra(ARG_FILE_PROGRESS, percentage);
        broadcaster.putExtra(ARG_FILE, template);
        broadcaster.putExtra(ARG_FILE_ERROR, ex);
        sendBroadcast(broadcaster);
    }*/
}

