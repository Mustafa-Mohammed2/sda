package com.example.project_external_data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class View_car_ditailes extends AppCompatActivity {

    private static final int IMAGE_REQ = 1;
    TextInputEditText et_modle ,et_color , et_description , et_dpl ;
    ImageView imageView;
    Toolbar toolbar;
    DataBase_Acces db;

    public static final int RESUIT_ADD =2;
    private Uri imageuri ;
    private int car_id=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car_ditailes);

        et_modle=findViewById(R.id.et_datials_modle);
        et_color=findViewById(R.id.et_datials_color);
        et_description=findViewById(R.id.et_datials_Description);
        et_dpl=findViewById(R.id.et_datials_dpl);
        imageView=findViewById(R.id.iv_image_datial);
        toolbar=findViewById(R.id.detail_toolbar);
       setSupportActionBar(toolbar);

        final Intent intent=getIntent();
        car_id= intent.getIntExtra(MainActivity.CAR_KEY,-1);

//        Toast.makeText(this, "id is "+car_id, Toast.LENGTH_SHORT).show();

        db=DataBase_Acces.getInstance_car(this);

        if (car_id == -1){
           enable_details();
           clear_ditals();
        }else{
            disable_details();
            db.open();
           Car c= db.get_search_id(car_id);
                      db.close();
          if(c!=null){
            fill_details(c);
                      }
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1,IMAGE_REQ);
            }
        });

    }


    private void fill_details(Car car){
//        if (car.getImage() !=null && !car.getImage().equals(""))
//            imageView.setImageURI(Uri.parse(car.getImage()));

        et_modle.setText(car.getModle());
        et_color.setText(car.getColor());
        et_dpl.setText(car.getDistansePerLetter()+"");
        et_description.setText(car.getDiscraption());

    }

        private void enable_details(){
            et_modle.setEnabled(true);
            et_color.setEnabled(true);
            et_description.setEnabled(true);
            et_dpl.setEnabled(true);
            imageView.setEnabled(true);
               }

    private void disable_details(){
        imageView.setEnabled(false);
        et_modle.setEnabled(false);
        et_color.setEnabled(false);
        et_description.setEnabled(false);
        et_dpl.setEnabled(false);
    }

    private void clear_ditals(){
        imageView.setImageURI(null);
        et_modle.setText("");
        et_color.setText("");
        et_description.setText("");
        et_dpl.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        MenuItem save=menu.findItem(R.id.detail_menu_save);
        MenuItem edit=menu.findItem(R.id.detail_menu_edit);
        MenuItem delete=menu.findItem(R.id.detail_menu_delete);

        if (car_id==-1){

            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);
        }else{
            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }
        return true;
                     }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String modle ,color, description,image;
        double dpl;

        switch (item.getItemId()){

            case R.id.detail_menu_save:
               // image = imageuri.toString();
               modle=et_modle.getText().toString();
               color=et_color.getText().toString();
               description=et_description.getText().toString();
               dpl=Double.parseDouble(et_dpl.getText().toString());

               Car car =new Car(modle,color,description,dpl);
               db.open();
               boolean resu= db.insert_car(car);
               db.close();

               if (resu){
//                   Toast.makeText(this, "image is"+ image, Toast.LENGTH_SHORT).show();
                   Toast.makeText(this, "car add sucsses", Toast.LENGTH_SHORT).show();
                   setResult(RESUIT_ADD,null);
                   finish();
               }


                return true;
            case R.id.detail_menu_edit:
                return true;
            case R.id.detail_menu_delete:
                return true;
        }
      return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMAGE_REQ && resultCode ==RESULT_OK){
          if (data!=null){
            imageuri=data.getData();
            imageView.setImageURI(imageuri);
        }
        }


    }
}