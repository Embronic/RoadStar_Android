package com.road.star.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;
import com.road.star.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.road.star.utils.Constants.mCamRequestedUri;

public class GeneralUtils {

    public static void showPictureDialog(Activity context) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                context.getString(R.string.camera),
                context.getString(R.string.gallery)};
        pictureDialog.setItems(pictureDialogItems,
                (dialog, which) -> {
                    switch (which) {
                        case 0:
                            chooseCamera(context, 100);
                            break;
                        case 1:
                            chooseGallery(context, 200);
                            break;
                    }
                });

        pictureDialog.show();
    }

    public static void chooseCamera(Activity activity, int requestCode) {
        mCamRequestedUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new ContentValues(1));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCamRequestedUri);
        activity.startActivityForResult(intent, requestCode);
    }


    public static void chooseGallery(Activity activity, int requestCode) {
        String[] mimetypes = {"image/*"};
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        activity.startActivityForResult(Intent.createChooser(intent, "Choose document to share.."), requestCode);
    }

    public static String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
