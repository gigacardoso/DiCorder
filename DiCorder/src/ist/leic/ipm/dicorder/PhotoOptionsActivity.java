package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PhotoOptionsActivity extends Activity implements Runnable {
	
	static final int HELP_DIALOG = 0;
	static final int IDENTIFY_DIALOG = 1;
	static final int POISON_ARROW_FROG = 0;
	static final int CORUNDUM = 1;
	ProgressDialog _dialog;
	private MediaStorage _media;
	private int _backgroundId;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_options_layout);
		
		_media = (MediaStorage) getIntent().getSerializableExtra("media");
		
		int backgroundId = getIntent().getIntExtra("backgroundId", 0);
		_backgroundId = backgroundId;
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.background_layout);
		layout.setBackgroundResource(backgroundId);
		
		Button save = (Button) findViewById(R.id.save);
		Button retake = (Button) findViewById(R.id.retake_photo);
		Button identify = (Button) findViewById(R.id.identify);
		
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int i = 0;
				while(!_media.addPicture("Picture " + i, _backgroundId)) {
					++i;
				}
				Intent intent = new Intent();
				intent.putExtra("media", _media);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		retake.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		identify.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_dialog = ProgressDialog.show(PhotoOptionsActivity.this, "", "Identifying...");
				Thread thread = new Thread(PhotoOptionsActivity.this);
				thread.start();
			}
		});
		
		ImageView help = (ImageView) findViewById(R.id.help_icon_photo_options);
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
	}
	
	public void run() {
		try{
			Thread.sleep(3000);
		}
		catch(InterruptedException e) {
			
		}
		_dialog.dismiss();
		Intent intent = new Intent();
		intent.putExtra("flag", getIntent().getIntExtra("flag", -1));
		intent.putExtra("media", _media);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id){
		case HELP_DIALOG:
			String helpMessage = getString(R.string.photo_options_help);
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

}