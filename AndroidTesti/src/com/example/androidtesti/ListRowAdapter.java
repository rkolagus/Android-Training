package com.example.androidtesti;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListRowAdapter extends ArrayAdapter<ListRow> {

	public ListRowAdapter(Context context, int textViewResourceId, List<ListRow> objects) {
		super(context, textViewResourceId, objects);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.activity_list_row_activity, parent, false);
			convertView.setTag(new ViewHolder(convertView));
		}

		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		ListRow item = getItem(position);

	    viewHolder.text.setText("" + item.numero);
	    viewHolder.image.setImageDrawable(item.kuva);

	    return convertView;
	}
	
	

	private static class ViewHolder {
	    final TextView text;
	    final ImageView image;
	    
	    public ViewHolder(View convertView) {
	        text = (TextView) convertView.findViewById(R.id.listRowActivity_tekstiKentta);
	        image = (ImageView) convertView.findViewById(R.id.listRowActivity_kuvaKentta);
	    }
	}

}