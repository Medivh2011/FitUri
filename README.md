# FitUri
快速完成Android 7 FileProvider适配,不与其他第三方冲突
## 使用
### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
<pre>
<code>
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
  </code>
  </pre>
### Step 2. Add the dependency
<pre>
<code>
	dependencies {
	        compile 'com.github.Medivh2011:FitUri:1.0'
	}
</code>
</pre>

## 使用示例
#### 1.TakePhoto
<pre>
<code>
private void takePhoto() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();
            //获取兼容N的Uri
            Uri fileUri = FileProviderN.getUriFromFile(this, file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(takePictureIntent, TAKE_PHOTO);
        }
    }

 @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == TAKE_PHOTO) {
           //todo 
        }
    }
</code>
</pre>
#### 2 InstallAPK
<pre>
<code>

  private void installApk() {
        // 需要自己修改安装包路径
        File file = new File(Environment.getExternalStorageDirectory(),
                "yourApp.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProviderN.setIntentDataAndType(this,
                intent, "application/vnd.android.package-archive", file, true);
        startActivity(intent);
    }
</code>
</pre>







