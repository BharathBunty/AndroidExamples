package com.example.bkanjula.cameraapp.CameraphotoApp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bkanjula.cameraapp.R;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {


    Bitmap bitmapCaptured;
    ImageView imageView_captured;
    String mCurrentPhotoPath;
    Uri picURi;
    Button pic;


    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;
    public static final int PICK_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imageView_captured = findViewById(R.id.image_capture);
        pic= findViewById(R.id.button_pick);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_press);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    openCamera();
                }
            }
        });
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("CameraApp",Context.MODE_PRIVATE);
        String image_uri = sharedPreferences.getString("pic",null);
        if(image_uri != null){
            Uri u = Uri.parse(image_uri);
            imageView_captured.setImageURI(u);
        }



//        if(savedInstanceState != null){
//            picURi = Uri.parse(savedInstanceState.getString("pic"));
//            imageView_captured.setImageURI(picURi);
//        }

//        bitmapCaptured = (Bitmap) getLastNonConfigurationInstance();
//        imageView_captured.setImageBitmap(bitmapCaptured);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                openCamera();
            } else {
                makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            assert data != null;
            bitmapCaptured = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            rotateBitmap(bitmapCaptured,90);
            imageView_captured.setImageBitmap(bitmapCaptured);

            picURi = data.getData();
            assert picURi != null;
            mCurrentPhotoPath = picURi.toString();

            SharedPreferences sharedPreferences = getSharedPreferences("CameraApp",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("pic",mCurrentPhotoPath);
            editor.apply();

//            picURi = data.getData();
//            assert picURi != null;
//            mCurrentPhotoPath = picURi.toString();
//            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//            intent.putExtra("uri",mCurrentPhotoPath);
//            startActivity(intent);
//            saveToGallery();

        }else if(requestCode== PICK_IMAGE ){

            picURi = data.getData();
            assert picURi != null;
            mCurrentPhotoPath = picURi.toString();
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("uri",mCurrentPhotoPath);
            startActivity(intent);



        }
    }

    private void saveToGallery() {

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {
        File storageDir = Environment.getExternalStorageDirectory();
        File image = File.createTempFile(
                "Image_Capture",  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("pic",picURi.toString());
//    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    private Bitmap rotateBitmap(Bitmap source, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSharedPreferences("CameraApp",Context.MODE_PRIVATE).edit().remove("pic").apply();
    }
}
