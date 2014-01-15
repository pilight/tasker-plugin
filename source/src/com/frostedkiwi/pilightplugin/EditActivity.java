package com.frostedkiwi.pilightplugin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.app.Activity;
import android.content.Intent;

public class EditActivity extends Activity {
	
	String mServer, mPort, mLocation, mDevice, mState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		setTitle("Settings");
	}
	
	public void finishActivity(View view){
		EditText mServerText = (EditText) findViewById(R.id.editTServerAddress);
		mServer = mServerText.getText().toString();
		EditText mPortText = (EditText) findViewById(R.id.editTPortNum);
		mPort = mPortText.getText().toString();
		EditText mLocationText = (EditText) findViewById(R.id.editTLocation);
		mLocation = mLocationText.getText().toString();
		EditText mDeviceText = (EditText) findViewById(R.id.editTDevice);
		mDevice = mDeviceText.getText().toString();
		RadioGroup g = (RadioGroup) findViewById(R.id.radioGState);
		int selected = g.getCheckedRadioButtonId();
		if (selected == R.id.radioOn){
			mState = "on";
		}
		else {
			mState = "off";
		}
		Intent i = new Intent();
		i.putExtra("Server", mServer).putExtra("Port", mPort).putExtra("Location", mLocation).putExtra("Device", mDevice).putExtra("State", mState);
		setResult(-1,i);
		finish();
	}

}

