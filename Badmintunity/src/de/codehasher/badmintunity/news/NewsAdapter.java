package de.codehasher.badmintunity.news;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.codehasher.badmintunity.R;

public class NewsAdapter extends ArrayAdapter<News> {

	private List<News> itemList;
	private Context context;

	public NewsAdapter(List<News> itemList, Context ctx) {
		super(ctx, R.layout.news_entry_layout, itemList);
		this.itemList = itemList;
		this.context = ctx;
	}

	@Override
	public int getCount() {
		if (itemList != null)
			return itemList.size();
		return 0;
	}

	@Override
	public News getItem(int position) {
		if (itemList != null)
			return itemList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (itemList != null)
			return itemList.get(position).hashCode();
		return 0;
	}

	public List<News> getItemList() {
		return itemList;
	}
			
	public void setItemList(List<News> itemList) {
		this.itemList = itemList;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.news_entry_layout, null);
		}

		News c = itemList.get(position);
		TextView text1 = (TextView) v.findViewById(R.id.textViewNewsId);
		text1.setText(c.getId());
		
		TextView text2 = (TextView) v.findViewById(R.id.textViewContent);
		text2.setText(c.getContent());

		TextView text3 = (TextView) v.findViewById(R.id.textViewTopic);
		text3.setText(c.getTopic());

		TextView text4 = (TextView) v.findViewById(R.id.textViewDate);
		text4.setText(c.getDate());

		return v;
	}
}
