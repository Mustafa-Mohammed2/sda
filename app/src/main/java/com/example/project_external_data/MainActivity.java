package com.example.project_external_data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_PERMISSION =4 ;
    RecyclerView rv;
    FloatingActionButton fab;
    Toolbar toolbar;
    private static final int CAR_REQ_ADD=1;
    private static final int CAR_REQ_EDIT=2;
    public static final String CAR_KEY="car_key";
    DataBase_Acces dataBase_acces;
    Recycler_Adapter_Car recycler_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.main_rv);
        fab=findViewById(R.id.main_fbtn);
        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar );

        dataBase_acces=DataBase_Acces.getInstance_car(this);
        ArrayList<Car>cars= new ArrayList<>();
//
//            cars.add(new Car(1,"2020","red","kia 2020","",20));
//            cars.add(new Car(2,"2019","asd","kia 2019","",19));
//            cars.add(new Car(3,"2010","green","kia 2010","",10));
        for (Car c: cars) {
            Log.d("car is",c.getId()+c.getModle()+c.getColor()+c.getDiscraption()+c.getDistansePerLetter());
        }
        dataBase_acces.open();
        cars=dataBase_acces.get_all_car();
        dataBase_acces.close();


         recycler_adapter=new Recycler_Adapter_Car(cars, new OnReceyclerlistener() {
            @Override
            public void onitemlistener(int car_id) {
                Intent i =new Intent(getApplicationContext(),View_car_ditailes.class);
                i.putExtra(CAR_KEY,car_id);
                startActivityForResult(i,CAR_REQ_EDIT);
            }
        });
        rv.setAdapter(recycler_adapter);
        RecyclerView.LayoutManager lm= new GridLayoutManager(this,2);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),View_car_ditailes.class);
                startActivityForResult(intent,CAR_REQ_ADD);
            }
        });

 if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED);
         String per [] ={Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this,per,REQ_PERMISSION);
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAR_REQ_ADD && requestCode==View_car_ditailes.RESUIT_ADD){
            if (data!=null){
                dataBase_acces.open();
              ArrayList<Car> c=new ArrayList<>();
                     c= dataBase_acces.get_all_car();
                dataBase_acces.close();
                recycler_adapter.setCars(c);
              recycler_adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       switch (requestCode){
           case REQ_PERMISSION :
           if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "تم الحصول على الصلاحيه", Toast.LENGTH_SHORT).show();
            }
           else {

           }
        }


    }
}