package ma.projet.my_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ma.projet.my_app.my_second_rv.Data;

public class ShoppingCartAdapter extends ArrayAdapter<Data> {

    public ShoppingCartAdapter(Context context, List<Data> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cart, parent, false);
        }

        Data item = getItem(position);

        ImageView itemImage = convertView.findViewById(R.id.item_image);
        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemPrice = convertView.findViewById(R.id.item_price);

        if (item != null) {
            itemImage.setImageResource(item.getImg());
            itemName.setText(item.getName_of_product());
            itemPrice.setText(item.getPrice());
        }

        return convertView;
    }
}
