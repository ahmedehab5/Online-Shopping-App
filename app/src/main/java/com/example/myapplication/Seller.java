package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Seller extends AppCompatActivity {


        //Database Version
    private static final int DATABASE_VERSION = 1;

    //DATABASE NAME:
    public static final String DATABASE_NAME = "product.db";


    //TABLE NAMES:
    public static final String TABLE_NAME = "products";


    EditText edit_name, edit_price,edit_disc;
    Button button_choose_img, button_add, button_list;
    ImageView image_view;

    final int REQUEST_CODE_GALLERY = 999;


    public static ProductsDBHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();

        sqLiteHelper = new ProductsDBHelper(this, DATABASE_NAME, null, DATABASE_VERSION);

        //Need to have CREATE TABLE IF NOT EXISTS otherwise it keeps crashing...
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (product_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT, image BLOB)");

        //Attach the onClickListener to the Button providing the functionality for the App to get
        // an Img from the Gallery into the ImageView in the Main Activity
        button_choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        Seller.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqLiteHelper.insertData(
                            edit_name.getText().toString().trim(),
                            edit_price.getText().toString().trim(),
                            imageViewToByte(image_view)
                    );
                    Toast.makeText(getApplicationContext(), "Added data successfully!", Toast.LENGTH_SHORT).show();
                    //Clear the fields to add new Data!
                    edit_name.setText("");
                    edit_price.setText("");
                    image_view.setImageResource(R.mipmap.ic_launcher);

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Seller.this, ProductList.class);
                startActivity(intent);
            }
        });

    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        byte[] byteArray= stream.toByteArray();
        return byteArray;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "You don't have permission to access the File Location.", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);

                //This code allows the user to find and insert an image into the placeholder image_view
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image_view.setImageBitmap(bitmap);
            } catch(FileNotFoundException e){
                e.printStackTrace();

            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //Returns an Object of the type View
                    public void init() {

                        //Links the TxtFlds & Btns on the Activity to the variables used

                        //This connects the variables to the Textfields on the Activity
                        edit_name = (EditText) findViewById(R.id.TxtDldName);
                        edit_price = (EditText) findViewById(R.id.TxtDldPrice);

                        //This connects the variables to the Buttons on the Activity
                        button_choose_img = (Button) findViewById(R.id.BtnChooseImage);
                        button_add = (Button) findViewById(R.id.BtnAddData);
                        button_list = (Button) findViewById(R.id.BtnListProduct);

                        //This connects the variable to the Image on the Activity
                        image_view = (ImageView) findViewById(R.id.ImgViewProduct);


                    }
                }

