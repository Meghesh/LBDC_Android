package com.project.LBDC;



import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;

public class Splash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		Thread spl=new Thread()
		{
			public void run()
			{
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Intent i=new Intent(Splash.this,LBDCActivity.class);
					startActivity(i);
				}
			}
		};
		spl.start();

	

}
}