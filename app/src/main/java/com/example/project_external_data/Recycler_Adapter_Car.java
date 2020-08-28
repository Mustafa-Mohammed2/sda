package com.example.project_external_data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter_Car extends RecyclerView.Adapter<Recycler_Adapter_Car.View_holder> {


    ArrayList<Car> cars;

    public Recycler_Adapter_Car(ArrayList<Car> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cat,null,false);
        View_holder view_holder =new View_holder(view);


        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {

        Car car=cars.get(position);
        holder.tv_modle.setText(car.getModle());
        holder.tv_color.setText(car.getColor());
        holder.tv_description.setText(car.getDiscraption());
        holder.tv_dpl.setText(car.getDistansePerLetter()+"");

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }




    public class View_holder extends RecyclerView.ViewHolder {

        TextView tv_modle;
        TextView tv_color;
        TextView tv_description;
        TextView tv_dpl;

        public View_holder(@NonNull View itemView) {
            super(itemView);

            tv_modle=itemView.findViewById(R.id.custom_car_modle);
            tv_color=itemView.findViewById(R.id.custom_car_color);
            tv_description=itemView.findViewById(R.id.custom_car_description);
            tv_dpl=itemView.findViewById(R.id.custom_car_dpl);
        }
    }





}

