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
	private HttpEntity httpEntity;//����Httpʵ��
	private InputStream inputStream;//��ȡhttp����Ҫת�������ļ����д���
	private HttpGetDataListener listener;//ʵ�ֽӿ�
	
	private String url;
	public HttpData(String url,HttpGetDataListener listener) {  //���췽��������ͨ�����췽������
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
			httpEntity=httpResponse.getEntity();//ͨ��response�����ȡ����
			inputStream=httpEntity.getContent();//ͨ��httpʵ���ȡ����getContent
			BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));//��ȡ���ݺ�ͨ�����������ж�ȡ
			String line=null;
			StringBuffer sb=new StringBuffer();
			while ((line=br.readLine())!=null) {
				sb.append(line);
			}
			return sb.toString();//ת��ΪString����.����Manifest�������
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return null;
	}
	//��ȡ�����ݺ�д����
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		listener.getDataUrl(result);//��������
		super.onPostExecute(result);
	}
	
}
