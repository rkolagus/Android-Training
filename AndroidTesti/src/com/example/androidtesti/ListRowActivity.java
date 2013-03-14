package com.example.androidtesti;

import java.util.LinkedList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ListRowActivity extends Activity {

	public ImageView kuva;
	LinkedList<Integer> animaatiolista;
	AnimationListener animationListener;
	boolean animaatioKaynnissa;
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
		kuva.setImageResource(intent.getIntExtra("RivinKuva",
				android.R.drawable.stat_notify_error));

		this.findViewById(R.id.listRowActivity_nappi_animaatio1).setVisibility(
				View.VISIBLE);
		this.findViewById(R.id.listRowActivity_nappi_animaatio2).setVisibility(
				View.VISIBLE);

		animaatioKaynnissa = false;
		animationListener = new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				animaatioKaynnissa = false;
				if (!animaatiolista.isEmpty()){
					animointi();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		};
		animaatiolista = new LinkedList<Integer>();
		animaatiolista.addLast(2);
		animointi();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setupActionBar() {
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

	public void animointi() {
		if (animaatiolista.isEmpty()) {
			return;
		}
		animaatioKaynnissa = true;
		Animation animaatio;
		int animaationumero;
		switch (animaatiolista.getFirst()) {
		case 1:
			animaationumero = android.R.anim.slide_out_right;
			break;
		case 2:
			animaationumero = android.R.anim.slide_in_left;
			break;
		case 3:
			animaationumero = android.R.anim.fade_out;
			break;
		case 4:
			animaationumero = android.R.anim.fade_in;
			break;
		default:
			return;
		}
		animaatio = AnimationUtils.loadAnimation(this, animaationumero);
		animaatiolista.removeFirst();
		animaatio.setFillAfter(true);
		animaatio.setAnimationListener(animationListener);
		kuva.startAnimation(animaatio);

	}

	public void animaatioYksi(View view) {
		animaatiolista.addLast(1);
		animaatiolista.addLast(2);
		this.animoidaanJosVoidaan();
	}

	public void animaatioKaksi(View view) {
		animaatiolista.addLast(3);
		animaatiolista.addLast(4);
		this.animoidaanJosVoidaan();
	}

	private void animoidaanJosVoidaan() {
		if (!animaatioKaynnissa) {
			animointi();
		}
	}
}
