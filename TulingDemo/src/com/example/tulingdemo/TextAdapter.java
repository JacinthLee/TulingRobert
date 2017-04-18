package com.example.tulingdemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TextAdapter extends BaseAdapter{

	private List<ListData> lists;//���ϵ���������
	private Context context;//�н�������
	private RelativeLayout layout;
	
	//�������췽��
	public TextAdapter(List<ListData> lists,Context context) {
		// TODO Auto-generated constructor stub
		this.lists=lists;
		this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();   //���ص�ǰlistView���ܳ��ص�����
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);   //���ص�ǰһ��id
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;  //���ص�ǰid
	}

	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		if (lists.get(position).getFlag()==ListData.RECEIVER) {
			layout=(RelativeLayout) inflater.inflate(R.layout.leftitem, null);
		}
		if (lists.get(position).getFlag()==ListData.SEND) {
			layout=(RelativeLayout) inflater.inflate(R.layout.rightitem, null);
		}
		TextView tv = (TextView) layout.findViewById(R.id.tv);
		//TextView time = (TextView) layout.findViewById(R.id.time);
		tv.setText(lists.get(position).getContent());
		//time.setText(lists.get(position).getTime());
		return layout;  //���ص�ǰview
	}

}
