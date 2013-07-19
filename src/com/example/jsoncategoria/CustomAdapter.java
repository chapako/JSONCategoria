package com.example.jsoncategoria;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	private ArrayList<Object> listData;
	private LayoutInflater layoutInflater;

	public CustomAdapter(Context contex, ArrayList<Object> list) {
		this.listData = list;
		this.layoutInflater = LayoutInflater.from(contex);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.listData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.listData.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		contenedor holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.formato, null);
			holder = new contenedor();
			holder.codigo = (TextView) convertView.findViewById(R.id.txtcodigo);
			holder.nombre = (TextView) convertView.findViewById(R.id.txtnombre);
			holder.descripcion = (TextView) convertView
					.findViewById(R.id.txtdescripcion);

			convertView.setTag(holder);
		} else {
			holder = (contenedor) convertView.getTag();
		}
		CategoriaEntidad nc;
		nc = (CategoriaEntidad) this.listData.get(position);
		holder.codigo.setText(String.valueOf(nc.getCodigo()));
		holder.nombre.setText(nc.getNomb());
		holder.descripcion.setText(nc.getDescrip());
		return convertView;
	}

	static class contenedor {
		TextView codigo;
		TextView nombre;
		TextView descripcion;
	}
}
