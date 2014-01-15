package com.frostedkiwi.pilightplugin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public final class FireReceiver extends BroadcastReceiver
{
	
	String mServer, mPort, mLocation, mDevice, mState;
	int mPortNum;
	
    @Override
    public void onReceive(final Context context, final Intent intent)
    {    	
    		Log.d("Receiver", "Fired");
        	mServer = intent.getStringExtra("Server");
        	mPort = intent.getStringExtra("Port");
        	mLocation = intent.getStringExtra("Location");
        	mDevice = intent.getStringExtra("Device");
        	mState = intent.getStringExtra("State");
        	JSONObject mCode = new JSONObject();
        	try {
        	    mCode.put("state", mState);
        	    mCode.put("device", mDevice);
        	    mCode.put("location", mLocation);
        	    
        	} catch (JSONException e) {
        	    // TODO Auto-generated catch block
        	    e.printStackTrace();
        	}
        	JSONObject mTCPObject = new JSONObject();
        	try {
        	    mTCPObject.put("message", "send");
        	    mTCPObject.put("code", mCode);
        	} catch (JSONException e) {
        	    // TODO Auto-generated catch block
        	    e.printStackTrace();
        	}
        	
    		try {
    			Socket socket = new Socket(mServer, Integer.parseInt(mPort));
    			OutputStream out = socket.getOutputStream();
    			PrintWriter output = new PrintWriter(out);
    			output.println("{\"message\":\"client controller\"}");
    			output.flush();
    			output.println(mTCPObject.toString());
    			Log.d("TCP", "Sent: " + mTCPObject.toString());
    			output.flush();
    			output.close();
    			socket.close();
    		} catch (UnknownHostException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		return;
    }
}
