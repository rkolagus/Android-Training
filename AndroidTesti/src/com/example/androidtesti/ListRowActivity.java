package com.example.androidtesti;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ListRowActivity extends Activity {


	public ImageView kuva;
	Animation animaatio;
	TextView numeroTeksti;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_row_activity);
		setupActionBar();
		
		Intent intent = getIntent();

		numeroTeksti = (TextView) findViewById(R.id.listRowActivity_tekstiKentta);
		numeroTeksti.setText("" + intent.getIntExtra("RivinNumero", 0));
		kuva = (ImageView) findViewById(R.id.listRowActivity_kuvaKentta);

		kuva.setImageResource(intent.getIntExtra("RivinKuva", android.R.drawable.stat_notify_error));
		this.findViewById(R.id.listRowActivity_nappi_animaatio1).setVisibility(View.VISIBLE);
		this.findViewById(R.id.listRowActivity_nappi_animaatio2).setVisibility(View.VISIBLE);
		//this.findViewById(R.id.listRowActivity_nappi_animaatio1).setEnabled(true);
		//this.findViewById(R.id.listRowActivity_nappi_animaatio2).setEnabled(true);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void animointi(int animaationumero){
		if (kuva.getAnimation() == null || kuva.getAnimation().hasEnded()){
			if (animaationumero == 1) {
				animaatio = AnimationUtils.loadAnimation(this,
						android.R.anim.slide_in_left);
			} else {
				animaatio = AnimationUtils.loadAnimation(this,
						android.R.anim.slide_out_right);
			}

			kuva.startAnimation(animaatio);
		}
	}
	
	public void animaatioYksi(View view){
		animointi(1);
	}
	public void animaatioKaksi(View view){
		animointi(2);
	}
}
