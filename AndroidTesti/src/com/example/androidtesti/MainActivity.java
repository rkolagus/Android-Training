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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		level = 1;
		textView = (TextView) findViewById(R.id.leveliTekstiNumero);
		textView.setText("" + level);
		listView = (ListView) findViewById(R.id.main_kuva_numero_lista);
		rivilista = new ArrayList<ListRow>();
		
		listRowAdapter = new ListRowAdapter(this, R.layout.activity_list_row_activity, rivilista);
		listRowAdapter.setMainActivity(this);
		listView.setAdapter(listRowAdapter);
		listView.setItemsCanFocus(true);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				uusiAktiviteetti(view, listRowAdapter.getKuva(position), position);
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

}
