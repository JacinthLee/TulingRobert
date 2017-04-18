package com.example.tulingdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements HttpGetDataListener,OnClickListener{

	private HttpData httpData;
	private List<ListData> lists;
	private ListView lv;//创建一个Adapter
	private EditText sendtext;
	private Button send_btn;
	private String content_str;
	private TextAdapter adapter;
	private String[] welcome_array;
	private double currentTime=0, oldTime = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    
    private void initView() {
		lists=new ArrayList<ListData>();
		//初始化页面
		lv=(ListView)findViewById(R.id.lv);
		sendtext=(EditText)findViewById(R.id.sendText);
		send_btn=(Button)findViewById(R.id.send_btn);
		send_btn.setOnClickListener(this);
		adapter = new TextAdapter(lists, this);
		lv.setAdapter(adapter);//绑定Adapter
		ListData listData;
		listData = new ListData(getRandomWelcomeTips(), ListData.RECEIVER,
				getTime());
		lists.add(listData);//重新适配
	}
    
	private String getRandomWelcomeTips() {
		String welcome_tip = null;
		welcome_array = this.getResources()  //获取资源
				.getStringArray(R.array.welcome_tips);
		int index = (int) (Math.random() * (welcome_array.length - 1));  //获取随机
		welcome_tip = welcome_array[index];
		return welcome_tip;
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
//			System.out.println(jb.getString("code"));
//		    System.out.println(jb.getString("text"));
			ListData listData;   
			listData=new ListData(jb.getString("text"),ListData.RECEIVER,
					getTime());
			lists.add(listData);  
			adapter.notifyDataSetChanged(); //点击或是系统返回数据的时候重新适配
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		getTime();
		content_str=sendtext.getText().toString();
		sendtext.setText("");//每次发送后输入框清空
		String dropk = content_str.replace(" ", "");//去掉空格
		String droph = dropk.replace("\n", "");//去掉回车
		ListData listData;
		listData=new ListData(content_str, ListData.SEND,getTime());
		lists.add(listData);//把内容放在集合当中
		if (lists.size() > 30) {//当数据量超过30条，就移除10条
			for (int i = 0; i < lists.size(); i++) {
				lists.remove(i);
			}
		}
		adapter.notifyDataSetChanged();//刷新
        httpData=(HttpData) new HttpData(
        		"http://www.tuling123.com/openapi/api?key=944cf063ca2f4455a76832cd17af4b06&info="
        		+droph, this).execute();//当点击的时候发送
	}
   
	private String getTime() {
		currentTime = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//显示时间格式
		Date curDate = new Date();
		String str = format.format(curDate);
		if (currentTime - oldTime >= 500) {//超过5分钟，显示新的时间
			oldTime = currentTime;
			return str;
		} else {
			return "";
		}

	}
}
