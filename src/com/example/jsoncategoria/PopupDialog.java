package com.example.jsoncategoria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class PopupDialog extends RelativeLayout {

	public PopupDialog(Context context) {
		super(context);
		inicializar();
		// TODO Auto-generated constructor stub
	}
	
	EditText editCod; 
	EditText editDescrip;
	EditText editNomb;
	OnClickListener listenerCancelar;
	
	private void inicializar(){
		LayoutInflater inflater;
		inflater= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.activity_nuevo, this , true);
		editCod = (EditText)findViewById(R.id.editNewCod);
		editDescrip= (EditText)findViewById(R.id.editNewDescrip);
		editNomb= (EditText)findViewById(R.id.editNewNomb);
		
	}
}
