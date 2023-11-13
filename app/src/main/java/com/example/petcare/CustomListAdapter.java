package com.example.petcare;
// CustomListAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<ListItem> {
    private Context context;
    private int resource;
    private ArrayList<ListItem> items;

    public CustomListAdapter(Context context, int resource, ArrayList<ListItem> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            listItemView = inflater.inflate(resource, parent, false);
        }

        ListItem currentItem = items.get(position);

        // Prikazivanje slike
        ImageView imageView = listItemView.findViewById(R.id.itemImage);
        imageView.setImageResource(currentItem.getImageResource());

        // Prikazivanje teksta (ime psa)
        TextView textView = listItemView.findViewById(R.id.itemText);
        textView.setText(currentItem.getText());

        return listItemView;
    }
}
