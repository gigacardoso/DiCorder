package ist.leic.ipm.dicorder;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class PicturesActivity extends Activity {
	
	static final int CAMERA = 4;
	static final int HELP_DIALOG = 0;
	static final int OPTIONS_DIALOG = 1;
	static final int PLAY_DIALOG = 2;
	
	private int _selectedImage;
	private String _selectedImageName;
	private MediaStorage _media;
	private ArrayList<String> _names;
	private ArrayList<Integer> _pictures;
	private int _selectedPosition;
	private PicturesAdapter _adapter;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_layout);
        
        _media = (MediaStorage) getIntent().getSerializableExtra("media");
        _pictures = _media.getPictures();
        _names = _media.getPicturesNames();
        
        _adapter = new PicturesAdapter(this, _pictures);
        GridView gridview = (GridView) findViewById(R.id.pictures_gridview);
		gridview.setAdapter(_adapter);
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				switch(position) {
				case 0:
					_selectedImage = _pictures.get(0);
					_selectedImageName = _names.get(0);
					showDialog(OPTIONS_DIALOG);
					break;
				case 1:
					_selectedImage = _pictures.get(1);
					_selectedImageName = _names.get(1);
					showDialog(OPTIONS_DIALOG);
					break;
				case 2:
					_selectedImage = _pictures.get(2);
					_selectedImageName = _names.get(2);
					showDialog(OPTIONS_DIALOG);
					break;
				case 3:
					_selectedImage = _pictures.get(3);
					_selectedImageName = _names.get(3);
					showDialog(OPTIONS_DIALOG);
					break;
				case 4:
					_selectedImage = _pictures.get(4);
					_selectedImageName = _names.get(4);
					showDialog(OPTIONS_DIALOG);
					break;
				case 5:
					_selectedImage = _pictures.get(5);
					_selectedImageName = _names.get(5);
					showDialog(OPTIONS_DIALOG);
					break;
				}
				_selectedPosition = position;
			}
		});
		
		Button cameraButton = (Button) findViewById(R.id.pictures_camera_button);
		cameraButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = PicturesActivity.this.getIntent();
				intent.putExtra("launch_code", CAMERA);
				PicturesActivity.this.finish();
			}
		});
		
		ImageView help = (ImageView) findViewById(R.id.pictures_help_icon);
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
		case OPTIONS_DIALOG:
			builder.setMessage(_selectedImageName);
			builder.setPositiveButton(getString(R.string.view), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					Intent intent = new Intent();
					intent.setClass(PicturesActivity.this, PlayActivity.class);
					intent.putExtra("imageId", _selectedImage);
					PicturesActivity.this.startActivity(intent);
				}
			});
			builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();					
				}
			});
			builder.setNeutralButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					_pictures.remove(_selectedPosition);
					_names.remove(_selectedPosition);
					_adapter.notifyDataSetChanged();
					_media.removePicture(_selectedImageName);
					dialog.cancel();
				}
			});
			dialog = builder.create();
			break;
		case HELP_DIALOG:
			String helpMessage = getString(R.string.pictures_help);
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
			((AlertDialog) dialog).setMessage(_selectedImageName);
			break;
		default:
			break;
		}
	}
	
}