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

	MainActivity mainActivity;
	final static int kuvaYksi = android.R.drawable.btn_minus;
	final static int kuvaKaksi = android.R.drawable.btn_plus;

	public ListRowAdapter(Context context, int textViewResourceId,
			List<ListRow> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.activity_list_row_activity,
					parent, false);

			convertView.setTag(new ViewHolder(convertView));

		}

		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		// ListRow item = getItem(position);

		viewHolder.text.setText("" + position);
		viewHolder.image.setImageResource(this.getKuva(position));

		return convertView;
	}

	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	private static class ViewHolder {
		final TextView text;
		final ImageView image;

		public ViewHolder(View convertView) {
			text = (TextView) convertView
					.findViewById(R.id.listRowActivity_tekstiKentta);
			image = (ImageView) convertView
					.findViewById(R.id.listRowActivity_kuvaKentta);
		}
	}
	
	@Override
	public int getCount() {
		return this.mainActivity.listakoko;
	}

	public int getKuva(int position) {
		if (position % 5 == 0) {
			return kuvaYksi;
		}
		return kuvaKaksi;
	}

}
