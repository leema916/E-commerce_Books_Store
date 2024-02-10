package ma.projet.my_app.my_second_rv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.projet.my_app.ItemSelectedActivity;
import ma.projet.my_app.R;

public class RecycleViewAdapter2 extends RecyclerView.Adapter<RecycleViewAdapter2.MyViewHolder> {
    Context context;
    List<Data> products_list;

    public RecycleViewAdapter2 ( Context context, List<Data> products_list){
        this.context = context;
        this.products_list = products_list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;
        TextView price;
        LinearLayout ly;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById (R.id.img_rv2);
            txt = itemView.findViewById(R.id.txt_rv2);
            price = itemView.findViewById(R.id.price_rv2);
            ly = itemView.findViewById(R.id.ly);
        }
    }

    @NonNull
    @Override
    public RecycleViewAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.the_cardview2_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter2.MyViewHolder holder, int position) {
        Data data_obj = products_list.get(position);

        holder.img.setImageResource(data_obj.getImg());
        holder.txt.setText(data_obj.getName_of_product());
        holder.price.setText(data_obj.getPrice());
        holder.ly.setOnClickListener(v->{
            Intent i = new Intent(context, ItemSelectedActivity.class);
            i.putExtra("itemName", data_obj.getName_of_product());
            i.putExtra("itemPhoto",data_obj.getImg());
            i.putExtra("itemPrice",data_obj.getPrice());
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return products_list.size();
    }
}
