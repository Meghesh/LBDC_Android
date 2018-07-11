package com.project.LBDC;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utils {

	public void setUsername(Context ctx, String email) {
		Editor file = ctx.getSharedPreferences("discount", 0).edit();
		file.putString("email", email);
		file.commit();
	
	}

	/*
	public void setSpin1(Context ctx, String ss3) {
		Editor file = ctx.getSharedPreferences("discount", 0).edit();
		file.putString("ss3", ss3);
		//file.commit();
		
	}
	
	public void setSpin2(Context ctx, String ss4) {
		Editor file = ctx.getSharedPreferences("discount", 0).edit();
		file.putString("ss4", ss4);
		//file.commit();
		
	}
	
	
	public String getSpin1(Context ctx) {
		SharedPreferences file = ctx.getSharedPreferences("discount", 0);
		return file.getString("ss3", "");
	}
	
	public String getSpin2(Context ctx) {
		SharedPreferences file = ctx.getSharedPreferences("discount", 0);
		return file.getString("ss4", "");
	}
	
	
	
	*/
	
	public String getUsername(Context ctx) {
		SharedPreferences file = ctx.getSharedPreferences("discount", 0);
		return file.getString("email", "");
	}
	
	public boolean isLogin(Context ctx) {
		SharedPreferences file = ctx.getSharedPreferences("discount", 0);
		return file.getBoolean("isLogin", false);
	}
	
	public void setLogin(Context ctx, boolean isLogin) {
		Editor file = ctx.getSharedPreferences("discount", 0).edit();
		file.putBoolean("isLogin", isLogin);
		file.commit();
	}
	
	public boolean isPropertyServiceRunning(Context ctx) {
	    ActivityManager manager = (ActivityManager) ctx.getSystemService(ctx.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if ("com.project.LBDC.PropertyService".equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}




}
