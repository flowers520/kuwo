package com.example.jim.kuwo;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {

	ArrayList<Music> arr;
	Context ctx;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 加载布局
		convertView=View.inflate(ctx, R.layout.music_layout, null);
		// 查找控件
		TextView tv_songName=(TextView)convertView.findViewById(R.id.tv_songName);
		TextView tv_singerName=(TextView)convertView.findViewById(R.id.tv_singerName);
		// 获取数据
		Music music = arr.get(position);
		// 赋值
		tv_songName.setText(music.songName);
		tv_singerName.setText(music.singerName);

		return convertView;
	}}
