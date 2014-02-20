package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MapActivity extends Activity {
	
	static final int CONTACTS = 0;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	static final int MAP_SETTINGS = 6;
	static final int HELP_DIALOG = 0;
	
	private int _backgroundId;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.map_background_layout);
		_backgroundId = getIntent().getIntExtra("backgroundId", R.drawable.forest);
		layout.setBackgroundResource(_backgroundId);
		
		Button settingsButton = (Button) findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("launch_code", MAP_SETTINGS);
				intent.putExtra("backgroundId", _backgroundId);
				MapActivity.this.setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		ImageView helpIcon = (ImageView) findViewById(R.id.map_help_icon);
		helpIcon.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
		
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case HELP_DIALOG:
			String helpMessage = getString(R.string.map_help);
			builder.setMessage(helpMessage);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			dialog = builder.create();
			break;
		default:
			dialog = null;
			break;
		}
		return dialog;
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.map_menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent = new Intent();
    	switch(item.getItemId()) {
    	case R.id.contacts:
    		intent.putExtra("launch_code", CONTACTS);
    		setResult(RESULT_OK, intent);
    		finish();    		
    		return true;
    	case R.id.catalog:
    		intent.putExtra("launch_code", CATALOG);
    		setResult(RESULT_OK, intent);
    		finish();
    		return true;
    	case R.id.multimedia:
    		intent.putExtra("launch_code", MULTIMEDIA);
    		setResult(RESULT_OK, intent);
    		finish();
    		return true;
    	case R.id.camera:
    		intent.putExtra("launch_code", CAMERA);
    		setResult(RESULT_OK, intent);
    		finish();
    		return true;
    	case R.id.laser:
    		intent.putExtra("launch_code", LASER);
    		setResult(RESULT_OK, intent);
    		finish();    		
    		return true;
    	case R.id.home_menu:
    		finish();
    	default:
    		return super.onOptionsItemSelected(item);	
    	}
    }

}