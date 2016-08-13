package com.example.jim.kuwo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MyTimerActivity extends Activity {

	TextView tv_color;//变颜色的标签
	TextView tv_shang;//闪烁的标签
	TextView tv_pig;//小猪快跑的标签
	Random rand=new Random();//声明全局随机器
	// 声明定时器
	Timer tmr1;//色色色定时器
	Timer tmr2;//闪闪闪定时器
	Timer tmr3;//小猪快跑定时器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_timer);

		tv_color=(TextView) findViewById(R.id.tv_color);
		tv_shang=(TextView) findViewById(R.id.tv_shang);
		tv_pig=(TextView) findViewById(R.id.tv_pig);

		// 创建小猪快跑定时器
		tmr3=new Timer();
		// 启动定时器
		tmr3.schedule(new TimerTask() {
			@Override
			public void run() {
				hand.sendEmptyMessage(3);
			}
		}, 0, 100);
	}

	public void begin1(View view){//色色色特效-开始
		// 创建定时器
		tmr1=new Timer();
		// 启动定时器（1.指定要做的事情，2.指定间隔时间）
		tmr1.schedule(
				new TimerTask() {
					@Override
					public void run() {
						// 向handler向'1'消息
						hand.sendEmptyMessage(1);
					}
				}, // task要做的事情
				0, // delay延迟（一般设为0,立即生效）
				100 // period间隔（以毫秒为单位）
		);
	}
	public void end1(View view){//色色色特效-结束
		tmr1.cancel();
	}

	public void begin2(View view){//闪闪闪特效-开始
		// 创建定时器
		tmr2=new Timer();
		// 启动定时器（1.指定要做的事情，2.指定间隔时间）
		tmr2.schedule(new TimerTask() {
			public void run() {
				hand.sendEmptyMessage(2);//发送闪闪闪的消息
			}
		}, 0, 100);

	}
	public void end2(View view){//闪闪闪特效-结束
		// 设置标签隐藏
		tmr2.cancel();
	}

	// 创建全局的Handler句柄为定时器做界面的处理
	Handler hand=new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			if(msg.what==1){// 色色色特效
				// 随机产生颜色
				int color = Color.rgb(rand.nextInt(256),
						rand.nextInt(256),
						rand.nextInt(256));
				tv_color.setTextColor(color);
			}else if(msg.what==2){// 闪闪闪特效
				// 判断当前标签的可见状态（反着来）
				if(tv_shang.getVisibility()==View.VISIBLE){
					tv_shang.setVisibility(View.INVISIBLE);
				}else{
					tv_shang.setVisibility(View.VISIBLE);
				}
			}else if(msg.what==3){// 小猪快跑特效
				// 获取原值
				String str=tv_pig.getText()+"";
				str=" "+str;//前置填空格
				// 判断是否到终点
				if(str.length()==9){
					str=str.trim();//去左右控件,回到起点
				}
				// 重新赋值
				tv_pig.setText(str);
			}
			return false;
		}
	});
}
