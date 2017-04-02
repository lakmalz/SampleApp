package lakmalz.git.sampleapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lakmal Weerasekara on 2/4/17.
 */

public class ImageUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    /**
    * This is from colouring app, this file getting from local storage from device
     */
    /*private void upload(String name) {
        Uri uri = Uri.parse("file://" + mArtTemplate.save_path);

        MultipartBody.Part body = FileUtils.prepareFilePart("post_image", uri, this);

        RequestBody postName = FileUtils.createPartFromString(name);
        RequestBody templateID = FileUtils.createPartFromString(String.valueOf(mArtTemplate.template_id));
        RequestBody token = FileUtils.createPartFromString(getToken());

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("post_name", postName);
        map.put("template_id", templateID);
        map.put("token", token);
        uploadSync.uploadPost(this, map, body);
    }*/

    /**
     * this from fm attachment source that download file getting from imageview
     */
    /*public void addAttachment(String path, Uri uri) {
        Bitmap bitmap = null;

        try {
            if (Utility.IsEmpty(path)) {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            } else {
                bitmap = BitmapFactory.decodeFile(path);
            }

            bitmap = new Utility().getResizedBitmap(bitmap);
            String tempPath = Utility.IsEmpty(path) ? Utility.createNewPath() : path;

            if (Utility.IsEmpty(path))
                Utility.createdTempImageFile(bitmap, tempPath);

            if (!Utility.IsEmpty(tempPath))
                bitmap = Utility.rotateImage(bitmap, tempPath, mActivity);

            File fileToSend = Utility.compressFile(bitmap, tempPath);
            if (fileToSend == null) {
                showMsgFailedImageSelect();
                return;
            }

            String guId = makeGUID();
            adapter.insert(new AddAttachment("Test", "100", tempPath, guId, true, false, true, "", true), 0);
            mRvAttach.scrollToPosition(0);
            mDelegate.onUploadingStatus(+1);

            new MultiPartUploadSync().uploadImage(this, mBaseUrl, mApiKey, mObjectInfo.getUploadFolder(), fileToSend, guId);
            *//*bitmap.recycle();*//*// TODO: 20/10/2016 check is this recycler
        } catch (Exception e) {
            showMsgFailedImageSelect();
        }
    }*/


    /**
     * Register broadcast receiver
     */
    /*@Override
    protected void onResume() {
        super.onResume();
        String broadcaster = getResources().getString(R.string.service_artwork_download_broadcast);

        IntentFilter filter = new IntentFilter(broadcaster);

        receiver = new ArtworkDownloadReceiver();
        registerReceiver(receiver, filter);
    }*/

    /**
     * unregister broadcast receiver
     */
    /*@Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }*/

    /**
     * This is start file download action
     */
    /*private void downloadArtwork(Template template) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(Constant.TEMPLATE_OBJECT, template);
        startService(intent);
    }*/

    /**
     * Broadcast receiver
     */
    /*class ArtworkDownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(ArtworkDownloadService.ARG_FILE_STATUS, -1);

            switch (status) {
                case ArtworkDownloadService.DOWNLOAD_PROCESSING:
                    updateProgress(intent);
                    break;

                case ArtworkDownloadService.DOWNLOAD_SUCCESS:
                    updateScreen(intent);
                    break;

                case ArtworkDownloadService.DOWNLOAD_ERROR:
                    break;

                default:
                    break;
            }
        }

        private void updateScreen(Intent arg1) {
            Template temp = (Template) arg1.getSerializableExtra(ArtworkDownloadService.ARG_FILE);
            template.setDownloaded(true);
            if (template != null) {
                if (template.getTemplate_id() == temp.template_id) {
                    template.save(mContext, mRealm, temp, false);
                    setTemplateView(template);
                    template.setDownloaded(true);
                    template.setSave_path(temp.getSave_path());
                    isDownloading = false;
                }
            }
        }

        private void updateProgress(Intent arg1) {
            int vidid = arg1.getIntExtra(ArtworkDownloadService.ARG_FILE_ID, -1);

            if (svrid != -1) {
                if (svrid == vidid) {
                    if (progressContent.getVisibility() == View.GONE) {
                        progressContent.setVisibility(View.VISIBLE);
                    }
                    Double progress = arg1.getDoubleExtra(ArtworkDownloadService.ARG_FILE_PROGRESS, 0);
                    progressBar.setProgress(progress.intValue());
                    isDownloading = true;
                }
            }
        }

        private void setTemplateView(Template video) {
            btnGet.setText("Open");
            progressContent.setVisibility(View.GONE);
        }
    }*/
}
