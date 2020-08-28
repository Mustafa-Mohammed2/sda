package com.example.project_external_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         rv=findViewById(R.id.recycler_main);

        DataBase_Acces dataBase_acces=DataBase_Acces.getInstance_car(this);
        ArrayList<Car>cars=new ArrayList<>();

//            cars.add(new Car(1,"2020","red","kia 2020",20));
//            cars.add(new Car(2,"2019","asd","kia 2019",19));
//            cars.add(new Car(3,"2010","green","kia 2010",10));
////
        dataBase_acces.open();
        cars=dataBase_acces.get_all_car();
        dataBase_acces.close();

//        for (Car c: cars) {
//            Log.d("car is",c.getId()+c.getModle()+c.getColor()+c.getDiscraption()+c.getImage()+c.getDistansePerLetter());
//        }
        Recycler_Adapter_Car recycler_adapter=new Recycler_Adapter_Car(cars);
        RecyclerView.LayoutManager lm= new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(recycler_adapter);

    }
}