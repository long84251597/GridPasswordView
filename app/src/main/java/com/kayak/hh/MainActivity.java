package com.kayak.hh;

import com.jungly.gridpasswordview.GridPasswordView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private SharedPreferences sp;
    private Button pop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	private void initView() {
		pop = (Button) findViewById(R.id.pop);
		pop.setOnClickListener(this);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("pass", "123456");
		editor.commit();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pop:
			AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("请输入密码");
			View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_layout, null);
			builder.setView(inflate);
			final GridPasswordView gv = (GridPasswordView) inflate.findViewById(R.id.gv);
			builder.setNegativeButton("确定",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String pass = sp.getString("pass","666666");
					if(gv.getPassWord().equals(pass)){
						startActivity(new Intent(MainActivity.this,SecondActivity.class));
						overridePendingTransition(R.anim.next_in_anim,R.anim.next_out_anim);
					}
				}
			});
			builder.setPositiveButton("取消",null);
			builder.create().show();
			break;
		}
	}

}
