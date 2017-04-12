package com.example.tulingdemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements HttpGetDataListener{

	private HttpData httpData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpData=(HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=944cf063ca2f4455a76832cd17af4b06&info=从人民广场到外滩怎么走", 
        		this).execute();
    }

	@Override
	public void getDataUrl(String data) {
		// TODO Auto-generated method stub
		//System.out.println(data);
		parseText(data);//调用解析的方法，把data传递进来
	}

	public void parseText(String str) {
		
		try {
			JSONObject jb=new JSONObject(str);
			System.out.println(jb.getString("code"));
			System.out.println(jb.getString("text"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   
}
