package com.example.bkanjula.cameraapp.LoginApp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bkanjula.cameraapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.makeText;

public class LoginDetailActivity extends AppCompatActivity {

    Bitmap bitmapCaptured;
    @BindView(R.id.image_capture)ImageView imageView_captured;
//    @BindView(R.id.button_cap)Button button_capture;
//    @BindView(R.id.button_next)Button button_next;
    Uri picURi;
    String mCurrentPhotoPath;

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_detail);
        ButterKnife.bind(this);

/*        imageView_captured = findViewById(R.id.image_capture);
        button_capture = findViewById(R.id.button_cap);
        button_next = findViewById(R.id.button_next);*/

    /*    button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(LoginDetailActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    }
                } else {
                    openCamera();
                }
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmapCaptured != null) {
                    Intent intent = new Intent(LoginDetailActivity.this, EmpDetailActivity.class);
                    intent.putExtra("picture", mCurrentPhotoPath);
                    intent.putExtra("FromActivity","LoginDetailActivity");
                    startActivity(intent);
                    finish();
                }
            }
        });*/

    }

    @OnClick(R.id.button_cap)
    public void buttonCapture(){
        if (ActivityCompat.checkSelfPermission(LoginDetailActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_PERMISSION_CODE);
            }
        } else {
            openCamera();
        }
    }

    @OnClick(R.id.button_next)
    public void buttonNext() {
        if (bitmapCaptured != null) {
            Intent intent = new Intent(LoginDetailActivity.this, EmpDetailActivity.class);
            intent.putExtra("picture", mCurrentPhotoPath);
            intent.putExtra("FromActivity","LoginDetailActivity");
            startActivity(intent);
            finish();
        }
    }


    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
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

    @Override
    protected void onResume() {
        super.onResume();
        String  from = getIntent().getStringExtra("FromActivity");
        if(from != null && from.equals("EmpDetailActivity")) {
            SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
            String image_uri = sharedPreferences.getString("pic", null);
            if (image_uri != null) {
                File imgFile = new File(image_uri);
                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imageView_captured.setImageBitmap(myBitmap);

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            assert data != null;
            bitmapCaptured = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            picURi = data.getData();

            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Camera");
            File file1 = new File(file.getAbsolutePath());
            long length = file1.length();
            length = length / 1024 / 1024;
            mCurrentPhotoPath = file.getAbsolutePath();
            picURi = Uri.fromFile(new File(mCurrentPhotoPath));
            try {
                mCurrentPhotoPath = saveImageBitmap(bitmapCaptured);
                imageView_captured.setImageBitmap(bitmapCaptured);
                SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pic", mCurrentPhotoPath);
                editor.apply();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

//    private File createImageFile() throws IOException {
//        File storageDir = Environment.getExternalStorageDirectory();
//        File image = File.createTempFile(
//                "Image_Capture",  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }

    }

    private String saveImageBitmap(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Camera");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
//            String str = file.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toString();
    }



    @Override public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(LoginDetailActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}