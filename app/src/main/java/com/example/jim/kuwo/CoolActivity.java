package com.example.jim.kuwo;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.CollapsibleActionView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CoolActivity extends Activity {

	ListView lv_music;// 列表控件
	TextView tv_playing;// 正播放标签
	MediaPlayer mplayer;// 声明播放器
	String sdcard;// 声明内存卡路径
	Timer tmr_jdt;// 声明进度条定时器
	ProgressBar progress;// 进度条控件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cool);

		sdcard = Environment.getExternalStorageDirectory().getPath();
		lv_music = (ListView) findViewById(R.id.lv_music);
		tv_playing = (TextView) findViewById(R.id.tv_playing);
		progress = (ProgressBar) findViewById(R.id.progressBar1);

		// 组装数据
		Music m1 = new Music("认真的雪", "薛之谦", "rzdx.mp3");
		Music m2 = new Music("三人游", "方大同", "sry.mp3");
		Music m3 = new Music("少年中国", "李宇春", "snzh.mp3");
		Music m4 = new Music("蜀绣", "李宇春", "sx.mp3");
		Music m5 = new Music("最炫民族风", "凤凰传奇", "zxmzf.mp3");
		Music m6 = new Music("女人心", "张学友", "nrx.mp3");
		Music m7 = new Music("黑白", "习大大", "hb.mp3");
		ArrayList<Music> arr = new ArrayList<Music>();
		arr.add(m1);
		arr.add(m2);
		arr.add(m3);
		arr.add(m4);
		arr.add(m5);
		arr.add(m6);
		arr.add(m7);
		// 创建适配器
		MusicAdapter ada = new MusicAdapter();
		ada.arr = arr;
		ada.ctx = this;
		// 设置listView适配器
		lv_music.setAdapter(ada);

		// 为listview添加项点击事件
		lv_music.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// 释放播放器
				if (mplayer != null) {
					mplayer.release();
				}

				// 获取点击的歌曲
				Music music = (Music) parent.getItemAtPosition(position);

				// 获取音乐的路径
				String path = sdcard+ "/" + music.path;
				// 创建播放器，并指定要播放的歌曲
				mplayer = MediaPlayer.create(CoolActivity.this,
						Uri.fromFile(new File(path)));
				// 播放
				mplayer.start();

				// 设置底面板的相关信息
				tv_playing.setText("正播放:" + music.songName);
				// 创建进度条定时器
				tmr_jdt = new Timer();
				// 启动定时器
				tmr_jdt.schedule(new TimerTask() {
					public void run() {
						// 给handler发送进度条信息
						hand.sendEmptyMessage(1);
					}
				}, 0, 10);
			}
		});
	}

	Handler hand = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 1) {// 进度条特效
				// 获取当前播放器的进度
				int curr = mplayer.getCurrentPosition();
				int total = mplayer.getDuration();
				// 操作进度条
				progress.setMax(total);//最大值
				progress.setProgress(curr);//当前值
			}
			return false;
		}
	});
}

