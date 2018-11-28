package com.example.bkanjula.cameraapp.LoginApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.bkanjula.cameraapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class UpdateActivity extends AppCompatActivity {

    int pos;
    ImageView img;
    TextInputEditText txt_email_update, txt_empId_update, txt_name_update;
    public static final int REQUEST_IMAGE_CAPTURE_UPDATE = 200;
    Bitmap bitmap_update;
    Uri picURi;
    String mCurrentPhotoPath_update , key_rowid;
    DataBaseHelper db;
    ArrayList<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new DataBaseHelper(getApplicationContext());
        assert getIntent() != null;
        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

        img = findViewById(R.id.image_update);
        txt_email_update = findViewById(R.id.email_update);
        txt_empId_update = findViewById(R.id.employee_id_update);
        txt_name_update = findViewById(R.id.employee_name_update);


        employeeDetailsList = db.getData();

        EmployeeDetails employeeDetails = employeeDetailsList.get(pos);
        txt_name_update.setText(employeeDetails.getName());
        txt_empId_update.setText(employeeDetails.getId());
        txt_email_update.setText(employeeDetails.getEmail());
        mCurrentPhotoPath_update = employeeDetails.getImage();
        key_rowid = employeeDetails.getKeyRowid();
        File imgFile = new File(employeeDetails.getImage());

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            img.setImageBitmap(myBitmap);

        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openCamera();
            }
        });

    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE_UPDATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.save) {

            String email = txt_email_update.getText().toString().trim();
            String empid = txt_empId_update.getText().toString().trim();
            String name = txt_name_update.getText().toString().trim();
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(empid) && !TextUtils.isEmpty(name) && mCurrentPhotoPath_update != null) {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                employeeDetails.setName(name);
                employeeDetails.setEmail(email);
                employeeDetails.setId(empid);
                employeeDetails.setKeyRowid(key_rowid);
                employeeDetails.setImage(mCurrentPhotoPath_update);
                db.updateData(pos,employeeDetails);
                Intent intent = new Intent(UpdateActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                return false;
            }

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE_UPDATE && resultCode == Activity.RESULT_OK) {

            assert data != null;
            bitmap_update = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            picURi = data.getData();

            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Camera");
            File file1 = new File(file.getAbsolutePath());
            long length = file1.length();
            length = length / 1024 / 1024;
            mCurrentPhotoPath_update = file.getAbsolutePath();
            picURi = Uri.fromFile(new File(mCurrentPhotoPath_update));
            try {
                mCurrentPhotoPath_update = saveImageBitmap(bitmap_update);
                img.setImageBitmap(bitmap_update);
//                SharedPreferences sharedPreferences = getSharedPreferences("CameraApp", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("pic_update", mCurrentPhotoPath_update);
//                editor.apply();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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

}
