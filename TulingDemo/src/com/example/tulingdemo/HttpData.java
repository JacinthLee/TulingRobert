package com.example.tulingdemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String>{

	private HttpClient httpClient;
	private HttpGet httpGet;
	private HttpResponse httpResponse;
	private HttpEntity httpEntity;//创建Http实体
	private InputStream inputStream;//获取http后需要转换成流文件进行处理
	private HttpGetDataListener listener;//实现接口
	
	private String url;
	public HttpData(String url,HttpGetDataListener listener) {  //构造方法。参数通过构造方法传递
		// TODO Auto-generated constructor stub
		this.url=url;
		this.listener=listener;
	}
	@Override
	protected String doInBackground(String... arg0) {
		try {
			httpClient=new DefaultHttpClient();
			httpGet=new HttpGet(url);
			httpResponse=httpClient.execute(httpGet);
			httpEntity=httpResponse.getEntity();//通过response对象获取数据
			inputStream=httpEntity.getContent();//通过http实体获取内容getContent
			BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));//获取内容后通过缓冲区进行读取
			String line=null;
			StringBuffer sb=new StringBuffer();
			while ((line=br.readLine())!=null) {
				sb.append(line);
			}
			return sb.toString();//转换为String类型.到”Manifest“中添加
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return null;
	}
	//获取到数据后复写方法
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		listener.getDataUrl(result);//返回数据
		super.onPostExecute(result);
	}
	
}
