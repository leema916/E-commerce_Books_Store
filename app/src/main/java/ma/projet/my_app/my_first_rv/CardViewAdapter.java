package ma.projet.my_app.my_first_rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.projet.my_app.R;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {

    private Context context;
    private List<CardItem> cardItemList;

    public CardViewAdapter(Context context, List<CardItem> cardItemList) {
        this.context = context;
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.the_cardview_row, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem cardItem = cardItemList.get(position);

        // Set data to your CardView items
        holder.imageView.setImageResource(cardItem.getImageResId());
        holder.textView.setText(cardItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    // ViewHolder class
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img); // Replace with the actual ID of your ImageView
            textView = itemView.findViewById(R.id.txt);   // Replace with the actual ID of your TextView
        }
    }
}

