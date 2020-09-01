package com.example.project_external_data;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter_Car extends RecyclerView.Adapter<Recycler_Adapter_Car.View_holder> {
    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    ArrayList<Car> cars;
    OnReceyclerlistener listener;

    public Recycler_Adapter_Car(ArrayList<Car> cars ,OnReceyclerlistener listener) {
        this.cars = cars;
        this.listener=listener;
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
//        if (car.getImage()!=null && !car.getImage().isEmpty()) {
//            holder.imageView.setImageURI(Uri.parse(car.getImage()));
//        }
        holder.tv_modle.setText(car.getModle());
        holder.tv_color.setText(car.getColor());
        holder.tv_description.setText(car.getDiscraption());
        holder.tv_dpl.setText(car.getDistansePerLetter()+"");

//      holder.imageView.setTag(car.getId());
         holder.id=car.getId();
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }




    public class View_holder extends RecyclerView.ViewHolder {

        TextView tv_modle,  tv_dpl, tv_description, tv_color;
          ImageView imageView;
      int id;

        public View_holder(@NonNull final View itemView) {
            super(itemView);
//            imageView=itemView.findViewById(R.id.custome_car_image);

            tv_modle=itemView.findViewById(R.id.custom_car_modle);
            tv_color=itemView.findViewById(R.id.custom_car_color);
            tv_description=itemView.findViewById(R.id.custom_car_description);
            tv_dpl=itemView.findViewById(R.id.custom_car_dpl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int id= (int) itemView.getTag();
                    listener.onitemlistener(id);
                }
            });
        }
    }





}

