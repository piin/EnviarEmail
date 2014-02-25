package com.piin.enviaremail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	private EditText nombre,telefono,extra;
	private CheckBox check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		nombre =  (EditText)findViewById(R.id.editText1);
		telefono =  (EditText)findViewById(R.id.editText2);
		extra =  (EditText)findViewById(R.id.editText3);
		check =(CheckBox)findViewById(R.id.checkBox1);		
		
	}
	
	public void onClick(View v) {
		//cambiar por destinatario tiene que ser un arreglo
        String[] correo = {"jair_p91@hotmail.com"} ;
        String mensaje = nombre.getText().toString() +"\n"+ telefono.getText().toString() +"\n"+ extra.getText().toString();
        String asunto = "asunto";
        
        if (check.isChecked()) {
        	enviar(correo,  asunto, mensaje);
		} else {
			Toast.makeText(MainActivity.this, "Acepta las condiciones de uso", Toast.LENGTH_SHORT).show();
		}
        
    }
 
    private void enviar(String[] correo, String asunto, String mensaje) {
    	//inivio intent y  paso parametros
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, correo);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");            
        try {
        	startActivity(Intent.createChooser(emailIntent, "Enviar correo con..."));
		} catch (android.content.ActivityNotFoundException ex) {
			// si no tiene clientes de correo
			Toast.makeText(MainActivity.this, "No tienes aplicaciones de correo", Toast.LENGTH_LONG).show();
		}
        
    }

}
