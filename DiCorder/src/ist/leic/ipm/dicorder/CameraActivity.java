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

public class CameraActivity extends Activity {
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	static final int HELP_DIALOG = 0;
	static final int POISON_ARROW_FROG = 0;
	static final int CORUNDUM = 1;
	
	private RelativeLayout _backgroundLayout;
	private int _currentBackground;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);

        if(savedInstanceState != null) {
        	_currentBackground = savedInstanceState.getInt("background");
        }
        else {
            _currentBackground = 0;
        }

        _backgroundLayout = (RelativeLayout) findViewById(R.id.background_layout);
        switch(_currentBackground) {
        case 0:
            _backgroundLayout.setBackgroundResource(R.drawable.poison_arrow_frog);
            break;
        case 1:
            _backgroundLayout.setBackgroundResource(R.drawable.corindon);
        	break;
        }
        
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        Button switchPhoto = (Button) findViewById(R.id.switch_photo);
        
        takePhoto.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent();
        		switch(_currentBackground) {
        		case 0:
            		intent.putExtra("backgroundId", R.drawable.poison_arrow_frog);
            		intent.putExtra("flag", POISON_ARROW_FROG);
        			break;
        		case 1:
            		intent.putExtra("backgroundId", R.drawable.corindon);
            		intent.putExtra("flag", CORUNDUM);
        			break;
        		}
        		intent.putExtra("media", getIntent().getSerializableExtra("media"));
        		intent.setClass(CameraActivity.this, PhotoOptionsActivity.class);
        		CameraActivity.this.startActivityForResult(intent, 1);
        	}
        });
        
        switchPhoto.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		switch(_currentBackground) {
        		case 0:
            		_backgroundLayout.setBackgroundResource(R.drawable.corindon);
            		_currentBackground = 1;
            		break;
        		case 1:
            		_backgroundLayout.setBackgroundResource(R.drawable.poison_arrow_frog);
            		_currentBackground = 0;
            		break;
        		}
        	}
        });
        
        ImageView help = (ImageView) findViewById(R.id.help_icon_camera);
        help.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		showDialog(HELP_DIALOG);
        	}
        });
    }
	
	protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
		outState.putInt("background", _currentBackground);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.camera_menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent = new Intent();
    	intent.putExtra("media", getIntent().getSerializableExtra("media"));
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
    	case R.id.laser:
    		intent.putExtra("launch_code", LASER);
    		setResult(RESULT_OK, intent);
    		finish();    		
    		return true;
    	case R.id.home_menu:
    		setResult(RESULT_OK, intent);
    		finish();
    	default:
    		return super.onOptionsItemSelected(item);	
    	}
    }
    
    protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id){
		case HELP_DIALOG:
			String helpMessage = getString(R.string.camera_help);
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
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
    	case 1:
    		if(data != null) {
    			setResult(RESULT_OK, data);
    			finish();
    		}
    		break;
    	}
	}

}