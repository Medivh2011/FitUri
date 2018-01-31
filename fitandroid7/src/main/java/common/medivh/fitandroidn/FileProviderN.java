package common.medivh.fitandroidn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

public class FileProviderN {

    public static Uri getUriFromFile(Context context, File file) {
        Uri fileUri = null;
        context = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = getUriForFile24(context, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    private static Uri getUriForFile24(Context context, File file) {
        Uri fileUri = FitAndroid7FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        return fileUri;
    }


    public static void setIntentDataAndType(Context context, Intent intent, String type, File file, boolean writeAble) {
       context = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setDataAndType(getUriFromFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }
}