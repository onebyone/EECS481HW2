package com.example.practice_project;

import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameView extends Activity {

	public final static String PLAYER_NAME = "player's name";
	public final static long WAIT_TIME = 3000;
	
	public String player1Name;
	public String player2Name;
	
	String player1Record = "";
	String player2Record = "";
	Vector<String> winPattern = new Vector<String>();
	
	boolean firstPlayer = true;
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_view);

		Intent intent = getIntent();
		player1Name = intent.getStringExtra(MainActivity.PLAYER_1_NAME);
		player2Name = intent.getStringExtra(MainActivity.PLAYER_2_NAME);
		winPattern.addElement("012");
		winPattern.addElement("036");
		winPattern.addElement("048");
		winPattern.addElement("147");
		winPattern.addElement("246");
		winPattern.addElement("258");
		winPattern.addElement("345");
		winPattern.addElement("678");
 	
		TextView title = (TextView)findViewById(R.id.whosturn);
		title.setText(player1Name + "'s turn (X)");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_view, menu);
		return true;
	}

	
	/** Called when the user clicks the Send button 
	 * @throws InterruptedException */
	public void clickMethod(View view) throws InterruptedException {
	    // Do something in response to button
		Button buttonView = (Button) view;
		
		if (buttonView.getText().length() != 0) return;
		
		int id = buttonView.getId() - R.id.button0;
		
		if (firstPlayer) {
			buttonView.setText("X");
			player1Record += "" + id;
		} else {
			buttonView.setText("O");
			player2Record += "" + id;
		}
		count++;
		
		if (count >= 5) {
			if (isPlayerWins(1)) {
				Intent intent = new Intent(this, DisplayMessageActivity.class);
				intent.putExtra(PLAYER_NAME, player1Name);
				Thread.sleep(WAIT_TIME);
				startActivity(intent);
			} else if (isPlayerWins(2)) {
				Intent intent = new Intent(this, DisplayMessageActivity.class);
				intent.putExtra(PLAYER_NAME, player2Name);
				Thread.sleep(WAIT_TIME);
				startActivity(intent);
			}
		} 
		if (count==9) {
			Intent intent = new Intent(this, DisplayMessageActivity.class);
			intent.putExtra(PLAYER_NAME, "");
			Thread.sleep(WAIT_TIME);
			startActivity(intent);
		}
		
		
		firstPlayer = !firstPlayer;
		
		TextView title = (TextView)findViewById(R.id.whosturn);
		if (firstPlayer) title.setText(player1Name + "'s turn(X)");
		else title.setText(player2Name + "'s turn(O)");
	}
	
	public boolean isPlayerWins(int player)
	{
		String playerRecord;
		if (player == 1)  playerRecord = player1Record;
		else playerRecord = player2Record;
		
		int size = winPattern.size();
		for (int i = 0; i < size; i++) {
			boolean match = true;
			for (int j = 0; j < 3; j++) {
				if (-1 == playerRecord.indexOf(winPattern.get(i).charAt(j))) {
					match = false;
					break;
				}
			}
			if(match) return true;
		}
		
		return false;
	}
}
