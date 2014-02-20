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
import android.widget.TextView;

public class LaserActivity extends Activity {
	
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	
	private int _thickness = 10;
	private int _length = 100;
	private boolean _on = false;
	static final int CONFIRM_ACTION_DIALOG = 0;
	static final int HELP_DIALOG = 1;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.laser_layout);
		
		if(savedInstanceState != null) {
			_thickness = savedInstanceState.getInt("thickness");
			_length = savedInstanceState.getInt("length");
			_on = savedInstanceState.getBoolean("on");
		}
		
		/*TextViews setup*/
		TextView tv = (TextView) findViewById(R.id.thickness);
		tv.setText(Integer.toString(_thickness));
		tv = (TextView) findViewById(R.id.length);
		tv.setText(Integer.toString(_length));
		tv = (TextView) findViewById(R.id.laser_state);
		tv.setText(getResources().getString(R.string.laser_off));
		
		/*Buttons setup*/
		Button b = (Button) findViewById(R.id.minus_thickness);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_thickness > 0) {
					--_thickness;
					updateDisplay();
				}
			}		
		});
		b = (Button) findViewById(R.id.double_minus_thickness);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_thickness > 9) {
					_thickness -= 10;
					updateDisplay();
				}
			}
		});
		b = (Button) findViewById(R.id.plus_thickness);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				++_thickness;
				updateDisplay();
			}
		});
		b = (Button) findViewById(R.id.double_plus_thickness);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_thickness += 10;
				updateDisplay();
			}
		});
		b = (Button) findViewById(R.id.minus_length);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_length > 0) {
					--_length;
					updateDisplay();
				}
			}
		});
		b = (Button) findViewById(R.id.double_minus_length);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_length > 9) {
					_length -= 10;
					updateDisplay();
				}
			}
		});
		b = (Button) findViewById(R.id.plus_length);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				++_length;
				updateDisplay();
			}
		});
		b = (Button) findViewById(R.id.double_plus_length);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_length += 10;
				updateDisplay();
			}
		});
		
		/*Button setup*/
		b = (Button) findViewById(R.id.laser_button);
		b.setText(getResources().getString(R.string.turn_on));
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_on) {
					_on = false;
					TextView tv = (TextView) findViewById(R.id.laser_state);
					tv.setText(getResources().getString(R.string.laser_off));
					Button b = (Button) findViewById(R.id.laser_button);
					b.setText(getResources().getString(R.string.turn_on));				
				}
				else {
					showDialog(CONFIRM_ACTION_DIALOG);
				}
			}
		});
		
		ImageView help = (ImageView) findViewById(R.id.help_icon_photo_options);
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
	}
	
	protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
		outState.putInt("thickness", _thickness);
		outState.putInt("length", _length);
		outState.putBoolean("on", _on);
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case CONFIRM_ACTION_DIALOG:
			builder.setMessage(getResources().getString(R.string.confirm_laser));
			builder.setPositiveButton(getString(R.string.turn_on), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					_on = true;
					TextView tv = (TextView) findViewById(R.id.laser_state);
					tv.setText(getResources().getString(R.string.laser_on));
					Button b = (Button) findViewById(R.id.laser_button);
					b.setText(getResources().getString(R.string.turn_off));				
				}
			});
			builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();					
				}
			});			
			dialog = builder.create();
			break;
		case HELP_DIALOG:
			String helpMessage = getString(R.string.laser_help);
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
	
	public void updateDisplay() {
		TextView tv = (TextView) findViewById(R.id.thickness);
		tv.setText(Integer.toString(_thickness));
		tv = (TextView) findViewById(R.id.length);
		tv.setText(Integer.toString(_length));		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.laser_menu, menu);
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
    	case R.id.map:
    		intent.putExtra("launch_code", MAP);
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
    	case R.id.home_menu:
    		finish();
    	default:
    		return super.onOptionsItemSelected(item);	
    	}
    }
	
}