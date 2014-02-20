package ist.leic.ipm.dicorder;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class AudioActivity extends Activity {

	static final int HELP_DIALOG = 0;
	static final int OPTIONS_DIALOG = 1;
	
	private MediaStorage _media;
	private ArrayList<String> _audioFiles;
	private int _selectedFile;
	private ArrayAdapter<String> _adapter;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_layout);

        _media = (MediaStorage) getIntent().getSerializableExtra("media");
        _audioFiles = _media.getAudio();
        
		ImageView help = (ImageView) findViewById(R.id.audio_help_icon);
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
        
		_adapter = new ArrayAdapter<String>(this, R.layout.list_item, _audioFiles);
        ListView audioList = (ListView) findViewById(R.id.audio_list);
        audioList.setTextFilterEnabled(true);
        audioList.setAdapter(_adapter);
        audioList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				_selectedFile = position;
				showDialog(OPTIONS_DIALOG);
		    }
		});
		
    }
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case OPTIONS_DIALOG:
			builder.setMessage(_audioFiles.get(_selectedFile));
			builder.setPositiveButton(getString(R.string.listen), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();					
				}
			});
			builder.setNeutralButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					_audioFiles.remove(_selectedFile);
					_adapter.notifyDataSetChanged();
					dialog.cancel();
				}
			});
			dialog = builder.create();
			break;
		case HELP_DIALOG:
			String helpMessage = getString(R.string.audio_help);
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

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch(id) {
		case OPTIONS_DIALOG:
			((AlertDialog) dialog).setMessage(_audioFiles.get(_selectedFile));
			break;
		default:
			break;
		}
	}

}