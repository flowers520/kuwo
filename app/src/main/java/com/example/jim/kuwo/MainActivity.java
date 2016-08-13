package com.example.jim.kuwo;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	// 声明全局播放器对象
	MediaPlayer mplayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// 播放
	public void play(View view) {
		/*
		 * uri 指:手机内的某一个资源 res 指:项目内的一个资源
		 */
		// 释放播放器（同一时刻只播放一首歌曲）
		if (mplayer != null) {
			mplayer.release();
		}

		// 获取手机内的sdcard路径
//		String path = Environment.getExternalStorageDirectory().getPath();
		String path = ""
		// 创建uri（指向于一个音乐文件）
		Uri uri = Uri.fromFile(new File(path + "/nrx.mp3"));
		// 创建播放器同时指定歌曲
		mplayer = MediaPlayer.create(this, uri);
		// 开始播放
		mplayer.start();
	}

	// 暂停
	public void pause(View view) {
		// 判断当前播放器的是否处于播放的状态
		if (mplayer != null && mplayer.isPlaying()) {
			mplayer.pause();
		}
	}

	// 继续
	public void cont(View view) {
		// 判断当前播放器是否处于暂停的状态
		if (mplayer != null && !mplayer.isPlaying()) {
			mplayer.start();
		}
	}

	// 停止
	public void stop(View view){
		// 判断当前播放器是否为空
		if(mplayer!=null){
			mplayer.stop();
		}
	}
}
