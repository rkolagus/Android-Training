package com.example.androidtesti;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	int level, listakoko = 3000000;
	List<ListRow> rivilista;
	ListRowAdapter listRowAdapter;
	TextView textView;
	ListView listView;
	int kuvaYksi = android.R.drawable.btn_minus;
	int kuvaKaksi = android.R.drawable.btn_plus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		level = 1;
		textView = (TextView) findViewById(R.id.leveliTekstiNumero);
		textView.setText("" + level);
		listView = (ListView) findViewById(R.id.main_kuva_numero_lista);
		rivilista = new ArrayList<ListRow>();
		this.growList(0);
		listRowAdapter = new ListRowAdapter(this, R.layout.activity_list_row_activity, rivilista);
		listRowAdapter.setMainActivity(this);
		listView.setAdapter(listRowAdapter);
		listView.setItemsCanFocus(true);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListRow valittu = rivilista.get(position);
				uusiAktiviteetti(view, valittu.kuva, valittu.numero);

			}
		});
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /* Kutsutaan Kasvata-nappia painettaessa */
    public void kasvataLevelia(View view){
    	this.level++;
    	textView.setText("" + level);
    }
    
    public void uusiAktiviteetti(View view){
    	uusiAktiviteetti(view, android.R.drawable.stat_sys_warning, level);
    }
    
    public void uusiAktiviteetti(View view, int kuva, int numero){
    	Intent intent = new Intent(this, ListRowActivity.class);
    	intent.putExtra("RivinNumero", numero);
    	intent.putExtra("RivinKuva", kuva);

    	startActivity(intent);
    	
    }
    
    public void growList(int position){
		int loppukoko = rivilista.size() + 500;
		for (int i = rivilista.size(); i < listakoko && i < loppukoko; i++) {
			if (i % 10 == 0) {
				rivilista.add(new ListRow(kuvaKaksi, i + 1));
			} else {
				rivilista.add(new ListRow(kuvaYksi, i + 1));
			}

		}
		listRowAdapter = new ListRowAdapter(this, R.layout.activity_list_row_activity, rivilista);
		listRowAdapter.setMainActivity(this);
		listView.setAdapter(listRowAdapter);
		listView.post(new PositionFinder(position));
	}
    private class PositionFinder implements Runnable {
    	int position;
    	public PositionFinder(int position){
    		this.position = position;
    	}
        @Override
        public void run() {
            listView.setSelection(position);
        }
    }

}
