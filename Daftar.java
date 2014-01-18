package com.example.mk_00;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Daftar extends Activity{
	EditText un,pw,rpw,nl,al,nt,nh;
	RadioGroup jk;
	TextView error;
	Button simpan,keluar;
	private String url = "http://10.0.2.2/android/tambah1.php";
	
	@Override
	 public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daftar);
		un=(EditText)findViewById(R.id.ta_un);
		pw=(EditText)findViewById(R.id.ta_pa);
		rpw=(EditText)findViewById(R.id.ta_pa1);
		nl=(EditText)findViewById(R.id.ta_nm);
		al=(EditText)findViewById(R.id.ta_al);
		nt=(EditText)findViewById(R.id.ta_tlp);
		nh=(EditText)findViewById(R.id.ta_hp);
		jk=(RadioGroup)findViewById(R.id.jke);
		
		simpan=(Button)findViewById(R.id.sim);
		keluar=(Button)findViewById(R.id.out);
		error=(TextView)findViewById(R.id.error);
		simpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String type=null;
					switch (jk.getCheckedRadioButtonId()) {
					case R.id.pria:
						type="Pria";
						break;
					case R.id.perempuan:
						type="Perempuan";
						break;
					default:
						break;
					}
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("username", un.getText().toString()));
			postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));
			postParameters.add(new BasicNameValuePair("repassword", rpw.getText().toString()));
			postParameters.add(new BasicNameValuePair("nama", nl.getText().toString()));
			postParameters.add(new BasicNameValuePair("jekel", type));
			postParameters.add(new BasicNameValuePair("alamat", al.getText().toString()));
			postParameters.add(new BasicNameValuePair("nomor_tlp", nt.getText().toString()));
			postParameters.add(new BasicNameValuePair("nomor_hp", nh.getText().toString()));
			
				/*String valid="1";*/
			String response=null;
			try{
				response=Sambung1.eksekusiHttpPost(url, postParameters);
					String res=response.toString();
				res=res.trim();
				res=res.replaceAll("\\s+", "");
				error.setText(res);
					if(res.equals("3")) error.setText("Field Tidak Boleh Kosong");
					else if(res.equals("2"))
					error.setText("Data Ganda");
					else if(res.equals("1"))
						error.setText("Data Tersimpan Ke server");
				}
				catch(Exception e){
				un.setText(e.toString());	
				}
				}
		});
	}
	public void keluar(View theButton){
		Intent a = new Intent(this,Sambung.class);
		startActivity(a);
	}			
}
