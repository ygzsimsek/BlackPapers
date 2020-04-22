package com.blackwater.blackpapers.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blackwater.blackpapers.Common.Common;
import com.blackwater.blackpapers.Model.CategoryItem;
import com.blackwater.blackpapers.Model.WallpaperItem;
import com.blackwater.blackpapers.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity {

    //TODO: SET STRING VALUES FOR ITEMS
    ImageView imgUpload;
    Button btnBrowse,btnUpload;
    MaterialSpinner spinnerCategory;
    //MaterialSpinnerData
    Map<String,String> spinnerData = new HashMap<>();
    private Uri filePath;
    String categoryIdSelect="",directUrl="",nameOfFile="";
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        /* TODO: SET PASSWORD
        EditText txt = new EditText(this);
        txt.setHint("Enter Password");

        Editable password = txt.getText();
        String pw = "password"";
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setView(txt);
        ab.setCancelable(true);
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(password == "karasu54"){
                    Toast.makeText(UploadActivity.this, "adsadsad", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ab.show();*/

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        imgUpload = (ImageView)findViewById(R.id.img_upload);
        btnBrowse = (Button)findViewById(R.id.btn_browse);
        btnUpload = (Button)findViewById(R.id.btn_upload);
        spinnerCategory = (MaterialSpinner)findViewById(R.id.spinner);

        //load spinnerdata
        loadCategoryToSpinner();

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerCategory.getSelectedIndex() == 0){
                    Toast.makeText(UploadActivity.this, "Please select category", Toast.LENGTH_SHORT).show();
                }else{
                    uploadImage();
                }
            }
        });
    }

    private void uploadImage() {
        if(filePath != null){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            nameOfFile = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child(new StringBuilder("images/").append(nameOfFile).toString());

            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            taskSnapshot.getStorage()
                                    .getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            directUrl = uri.toString();
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(UploadActivity.this, "Oh shit" , Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setTitle("Uploading"+(Double)progress+"%");
                            Toast.makeText(UploadActivity.this, "UPLOADING", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    private void saveUriToCategory(String categoryIdSelect, String imageLink) {
        FirebaseDatabase.getInstance()
                .getReference(Common.STR_CATEGORY_BACKGROUND)
                .push()// gen key
                .setValue(new WallpaperItem(imageLink,categoryIdSelect))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UploadActivity.this, "FUCK YEAH", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Common.PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgUpload.setImageBitmap(bitmap);
                btnUpload.setEnabled(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void chooseImage() {
        Intent browseImage = new Intent();
        browseImage.setType("image/*");
        browseImage.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(browseImage,"Select picture"),Common.PICK_IMAGE_REQUEST);
    }

    private void loadCategoryToSpinner() {
        FirebaseDatabase.getInstance()
            .getReference(Common.STR_WALLPAPER)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                            CategoryItem item = postSnapshot.getValue(CategoryItem.class);
                            String key = postSnapshot.getKey();
                            spinnerData.put(key,item.getName());
                        }
                        Object[] valueArray = spinnerData.values().toArray(); //get spinner data
                        List<Object> valueList = new ArrayList<>();
                        valueList.add(0,"Category");
                        valueList.addAll(Arrays.asList(valueArray)); //add all categories
                        spinnerCategory.setItems(valueList);
                        spinnerCategory.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                                //get categoryId key
                                Object[] keyArray = spinnerData.keySet().toArray();
                                List<Object> keyList = new ArrayList<>();
                                keyList.add(0,"Category_Key");
                                keyList.addAll(Arrays.asList(keyArray));
                                categoryIdSelect = keyList.get(position).toString(); //assign key
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
