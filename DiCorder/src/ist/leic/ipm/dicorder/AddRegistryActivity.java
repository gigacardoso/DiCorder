package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddRegistryActivity extends Activity {

	static final int CAMERA = 4;
	static final int HELP_DIALOG = 0;
	
	private Registry _registry;
	private MediaStorage _media;
	private EditText _nameField;
	private EditText _infoField;
	private int _selectedImage;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_registry_layout);
		
		_registry = (Registry) getIntent().getSerializableExtra("registry");
		_media = (MediaStorage) getIntent().getSerializableExtra("media");
		
		_nameField = (EditText) findViewById(R.id.name_field);
		_infoField = (EditText) findViewById(R.id.info_field);
		
		ImageView help = (ImageView) findViewById(R.id.registry_help_icon);
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
		
		Spinner photoSpinner = (Spinner) findViewById(R.id.photo_spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, _media.getPicturesNames());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		photoSpinner.setAdapter(adapter);
		photoSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				_selectedImage = _media.getPictures().get(pos);
			}
			public void onNothingSelected(AdapterView<?> parent){}
		});
		
		Button takePhoto = (Button) findViewById(R.id.registry_take_photo);
		takePhoto.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = AddRegistryActivity.this.getIntent();
				intent.putExtra("launch_code", CAMERA);
				AddRegistryActivity.this.finish();
			}
		});
		
		Button save = (Button) findViewById(R.id.registry_save_button);
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = _nameField.getText().toString();
				String info = _infoField.getText().toString();
				_registry.addInfo(name, info);
				_registry.addImage(name, _selectedImage);
			}
		});
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case HELP_DIALOG:
			String helpMessage = getString(R.string.add_registry_help);
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
