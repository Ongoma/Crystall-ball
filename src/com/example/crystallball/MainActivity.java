package com.example.crystallball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystallball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	public static final String TAG = MainActivity.class.getName();
 private Crystalball mCrystalBall = new Crystalball();
 private TextView mAnswerLabel;
 private ImageView mCrystalBallImage;
 private SensorManager mSensorManager;
 private Sensor mAccelerometer;
 private ShakeDetector mShakeDetector;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign the views from the layout.
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
        mSensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //shake listener
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				handleNewAnswer();
			}
		});
        /*Toast.makeText(this, "Yay! our activity was created", Toast.LENGTH_LONG).show();
        Toast welcomeToast = Toast.makeText(this, "Look at me up here", Toast.LENGTH_LONG);
        welcomeToast.setGravity(Gravity.TOP, 0, 0);
        welcomeToast.show();*/
        Log.d(TAG, "We are logging from the onCreate() method");
        }
    @Override
    public void onResume(){
    	super.onResume();
    	mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    public void onPause(){
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    }
    //method to animate the crystallBall
    private void animateCrystalBall(){
    	mCrystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation = (AnimationDrawable)mCrystalBallImage.getDrawable();
    	
    	if(ballAnimation.isRunning()){
    		ballAnimation.stop();
    	}
    	ballAnimation.start();
    }
   private void animateAnswer(){
	   AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
	   fadeInAnimation.setDuration(1500);
	   fadeInAnimation.setFillAfter(true);
	   mAnswerLabel.setAnimation(fadeInAnimation);
   }
   private void playSound(){
	   MediaPlayer player = MediaPlayer.create(this,R.raw.crystal_ball);
	   player.start();
	   player.setOnCompletionListener(new OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			mp.release();
		}
	});
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	private void handleNewAnswer() {
		String answer = mCrystalBall.getAnswer();
		mAnswerLabel.setText(answer);
		//calling the function
		animateCrystalBall();
		animateAnswer();
		playSound();
	}
    
}
