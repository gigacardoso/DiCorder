package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	static final int HELP_DIALOG = 0;
	
	private LinearLayout _trackingLayout;
	private LinearLayout _frogLayout;
	private LinearLayout _corundumLayout;
	private boolean _frogSelected;
	private boolean _corundumSelected;
	private boolean _frogDisplayed = false;
	private boolean _corundumDisplayed = false;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_settings_layout);
		
		setTrackings();
		switch(getIntent().getIntExtra("backgroundId", R.drawable.forest)) {
		case R.drawable.forest_frog:
			_trackingLayout.addView(_frogLayout);
			_frogDisplayed = true;
			break;
		case R.drawable.forest_corundum:
			_trackingLayout.addView(_corundumLayout);
			_corundumDisplayed = true;
			break;
		case R.drawable.forest_frog_corundum:
			_trackingLayout.addView(_frogLayout);
			_frogDisplayed = true;
			_trackingLayout.addView(_corundumLayout);
			_corundumDisplayed = true;
			break;
		}
		
		ImageView helpIcon = (ImageView) findViewById(R.id.settings_help_icon);
		helpIcon.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
		
		Spinner spinner = (Spinner) findViewById(R.id.settings_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.species_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				switch(pos) {
				case 0:
					_frogSelected = true;
					_corundumSelected = false;
					break;
				case 1:
					_corundumSelected = true;
					_frogSelected = false;
					break;
				}
			}
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
		Button addTrackingButton = (Button) findViewById(R.id.add_tracking_button);
		addTrackingButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_frogSelected && !_frogDisplayed) {
					_trackingLayout.addView(_frogLayout);
					_frogDisplayed = true;
				}
				if(_corundumSelected && !_corundumDisplayed) {
					_trackingLayout.addView(_corundumLayout);
					_corundumDisplayed = true;
				}
			}
		});
		
		Button doneButton = (Button) findViewById(R.id.done_button);
		doneButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("launch_code", MAP);
				if(_frogDisplayed && !_corundumDisplayed) {
					intent.putExtra("backgroundId", R.drawable.forest_frog);
				}
				if(!_frogDisplayed && _corundumDisplayed) {
					intent.putExtra("backgroundId", R.drawable.forest_corundum);
				}
				if(_frogDisplayed && _corundumDisplayed) {
					intent.putExtra("backgroundId", R.drawable.forest_frog_corundum);
				}
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case HELP_DIALOG:
			String helpMessage = getString(R.string.settings_help);
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
	
	private void setTrackings() {
		_trackingLayout = (LinearLayout) findViewById(R.id.tracking_layout);
		_trackingLayout.setHorizontalGravity(Gravity.CENTER);
		_frogLayout = new LinearLayout(SettingsActivity.this);
		_frogLayout.setOrientation(LinearLayout.HORIZONTAL);
		_frogLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		_corundumLayout = new LinearLayout(SettingsActivity.this);
		_corundumLayout.setOrientation(LinearLayout.HORIZONTAL);
		_corundumLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		TextView tV = new TextView(SettingsActivity.this);
		tV.setText(R.string.frog);
		Button b = new Button(SettingsActivity.this);
		b.setText(getString(R.string.remove));
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_trackingLayout.removeView(_frogLayout);
				_frogDisplayed = false;
			}
		});
		_frogLayout.addView(tV);
		_frogLayout.addView(b);
		tV = new TextView(SettingsActivity.this);
		tV.setText(R.string.corundum);
		b = new Button(SettingsActivity.this);
		b.setText(getString(R.string.remove));
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_trackingLayout.removeView(_corundumLayout);
				_corundumDisplayed = false;
			}
		});
		_corundumLayout.addView(tV);
		_corundumLayout.addView(b);
	}

}