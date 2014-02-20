package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CallActivity extends Activity {

	static final int DIALOG_HELP = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_layout);
		TextView tv = (TextView) findViewById(R.id.contact_name);
		String contactName = getIntent().getStringExtra("contact");
		tv.setText(contactName);
		tv = (TextView) findViewById(R.id.distance_text);
		if(contactName.equals("Daniel Cardoso")) {
			tv.setText("5 m");
		}
		if(contactName.equals("Francisco Raposo")) {
			tv.setText("300 m");
		}
		if(contactName.equals("Miguel Aragão")) {
			tv.setText("900 m");
		}
		if(contactName.equals("Carlos")) {
			tv.setText("1,5 Km");
		}
		ImageView iv = (ImageView) findViewById(R.id.hang_up_image);
		iv.setImageResource(R.drawable.ic_end_call_red);
		iv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		ImageView help = (ImageView) findViewById(R.id.help_icon);
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_HELP);
			}
		});
	}
	
	protected Dialog onCreateDialog(int id) {
		AlertDialog dialog;
		switch(id){
		case DIALOG_HELP:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			String helpMessage = getString(R.string.call_help);
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