package com.example.jsoncategoria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class Categoria extends Activity {

	public String readJSONFeed(String URL) {
		StringBuilder stringBuilder = new StringBuilder();
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URL);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream inputStream = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				String line;
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line);
				}
				inputStream.close();
			} else {
				Log.d("JSON", "Falló la descarga del archivo");
			}
		} catch (Exception e) {
			Log.d("readJSONFeed", e.getLocalizedMessage());
		}
		return stringBuilder.toString();
	}

	private class LeerCategoriaJSONFeedTask extends
			AsyncTask<String, Void, String> {
		protected String doInBackground(String... urls) {
			return readJSONFeed(urls[0]);
		}

		protected void onPostExecute(String result) {
			try {
				JSONObject jsonObject = new JSONObject(result);
				JSONArray CategoriaItems = new JSONArray(jsonObject.getString("GetCategoriaResult"));
				int i=0;   ArrayList<Object> lista = new ArrayList<Object>();
				while(i< CategoriaItems.length()){
					JSONObject objeto= CategoriaItems.getJSONObject(i);
					CategoriaEntidad categoria= new CategoriaEntidad(objeto.getInt("CodCategoria"),objeto.getString("DescripcionCategoria"),
							objeto.getString("NombreCategoria"));
				    lista.add(categoria);
				    i++;
				}
				CustomAdapter adapter= new CustomAdapter(getApplicationContext(), lista);
			    ListView l= (ListView) findViewById(R.id.listViewCategoria);
			    l.setAdapter(adapter);		    
			} catch (Exception e) {
				Log.d("LeerCategoriaJSONFeedTask", e.getLocalizedMessage());
			}
		}
		

		// Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
	}
	
	
	public void showSortPopup(View v) 
	{
		AlertDialog.Builder dialog= new Builder(this);
		dialog.setTitle("Nueva Categoria");
		PopupDialog view = new PopupDialog(this);
		dialog.setView(view);
		dialog.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();	
			}
		});
		dialog.show();
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria);
		new LeerCategoriaJSONFeedTask()
		.execute("http://172.21.15.4/ws/Service1.svc/Categoria/");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria, menu);
		return true;
	}

}
