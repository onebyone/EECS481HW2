package com.example.practice_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String PLAYER_1_NAME = "player 1's name";
	public final static String PLAYER_2_NAME = "player 2's name";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Send button */
	public void startGame(View view) {
	    // Do something in response to button
		Button buttonView = (Button)view;
		
		Intent intent = new Intent(this, GameView.class);
		EditText editText = (EditText) findViewById(R.id.player_1_name);
		String message = editText.getText().toString();
		intent.putExtra(PLAYER_1_NAME, message);
		
		editText = (EditText) findViewById(R.id.player_2_name);
		message = editText.getText().toString();
		intent.putExtra(PLAYER_2_NAME, message);
		startActivity(intent);
	}

}
