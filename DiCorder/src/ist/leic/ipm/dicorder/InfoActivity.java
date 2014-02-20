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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoActivity extends Activity {
	
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	
	static final int POISON_ARROW_FROG = 0;
	static final int CORUNDUM = 1;
	static final int HELP_DIALOG = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_layout);
		
		ImageView image = (ImageView) findViewById(R.id.image);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		image.setLayoutParams(params);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		TextView text = (TextView) findViewById(R.id.info);
		
		switch(getIntent().getIntExtra("flag", -1)) {
		case POISON_ARROW_FROG:
			image.setImageResource(R.drawable.poison_arrow_frog1);
			text.setText(R.string.frog_info);
			setTitle(getString(R.string.frog));
			break;
		case CORUNDUM:
			image.setImageResource(R.drawable.corindon1);
			text.setText(R.string.corundum_info);
			setTitle(getString(R.string.corundum));
			break;
		case -1:
			text.setText(getIntent().getStringExtra("info"));
			image.setImageResource(getIntent().getIntExtra("image", -1));
			setTitle(getIntent().getStringExtra("name"));
			break;
		}
		
		ImageView help = (ImageView) findViewById(R.id.help_icon_info);
		help.setOnClickListener(new OnClickListener() {
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
			String helpMessage = getString(R.string.info_help);
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
    	inflater.inflate(R.menu.info_menu, menu);
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