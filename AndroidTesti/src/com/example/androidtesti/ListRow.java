package com.example.androidtesti;

public class ListRow {
	public final int numero, kuva;

	public ListRow(int kuva, int numero){
		this.numero = numero;
		this.kuva = kuva;
	}
	
	public String getText() {
		return "" + this.numero;
	}
	
	public final int getKuva(){
	    return kuva;
	}
}
