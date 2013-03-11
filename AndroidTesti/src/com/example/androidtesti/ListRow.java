package com.example.androidtesti;

import android.graphics.drawable.Drawable;

public class ListRow {
	int numero;
	Drawable kuva;

	public ListRow(Drawable kuva, int numero){
		this.numero = numero;
		this.kuva = kuva;
	}
	
	public String getText() {
		return "" + this.numero;
	}
	
	public final Drawable getImageDrawable(){
		return kuva;
	}
}
