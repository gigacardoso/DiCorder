package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MenuActivity extends Activity {
	
	static final int CONTACTS = 0;
	static final int MAP = 1;
	static final int CATALOG = 2;
	static final int MULTIMEDIA = 3;
	static final int CAMERA = 4;
	static final int LASER = 5;
	static final int MAP_SETTINGS = 6;
	static final int INFO = 7;
	static final int POISON_ARROW_FROG = 0;
	static final int CORUNDUM = 1;
	
	private int _backgroundId;
	private static Registry _registry;
	private static MediaStorage _media;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		
		if(savedInstanceState == null) {
			_registry = new Registry();
			_registry.addInfo("Poison Arrow Frog", getResources().getString(R.string.frog_info));
			_registry.addImage("Poison Arrow Frog", R.drawable.poison_arrow_frog1);
			_registry.addInfo("Corundum", getResources().getString(R.string.corundum_info));
			_registry.addImage("Corundum", R.drawable.corindon1);
			
			_media = new MediaStorage();
			_media.addPicture("Corundum", R.drawable.corindon1);
			_media.addPicture("Poison Arrow Frog", R.drawable.poison_arrow_frog1);
			_media.addPicture("Venus Flytrap", R.drawable.venus_flytrap);
			_media.addPicture("Artic Fox", R.drawable.artic_fox);
			_media.addVideo("Bear", R.drawable.bear);
			_media.addVideo("Tiger", R.drawable.tiger);
			_media.addVideo("Squirrel", R.drawable.squirrel);
			_media.addVideo("Duck", R.drawable.duck);
			_media.addAudio(7);
			
			_backgroundId = R.drawable.forest;
		}
		else {
			_registry = (Registry) savedInstanceState.getSerializable("registry");
			_media = (MediaStorage) savedInstanceState.getSerializable("media");
			_backgroundId = savedInstanceState.getInt("backgroundId");
		}
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent intent = new Intent();
				switch(position) {
				case 0:
					intent.setClass(MenuActivity.this, ContactsActivity.class);
					MenuActivity.this.startActivityForResult(intent, CONTACTS);
					break;
				case 1:
					intent.setClass(MenuActivity.this, MapActivity.class);
					intent.putExtra("backgroundId", _backgroundId);
					MenuActivity.this.startActivityForResult(intent, MAP);
					break;
				case 2:
					intent.setClass(MenuActivity.this, CatalogActivity.class);
					intent.putExtra("registry", _registry);
					intent.putExtra("media", _media);
					MenuActivity.this.startActivityForResult(intent, CATALOG);
					break;
				case 3:
					intent.setClass(MenuActivity.this, MultimediaActivity.class);
					intent.putExtra("media", _media);
					MenuActivity.this.startActivityForResult(intent, MULTIMEDIA);
					break;
				case 4:
					intent.setClass(MenuActivity.this, CameraActivity.class);
					intent.putExtra("media", _media);
					MenuActivity.this.startActivityForResult(intent, CAMERA);
					break;
				case 5:
					intent.setClass(MenuActivity.this, LaserActivity.class);
					MenuActivity.this.startActivityForResult(intent, LASER);
					break;
				}
			}
		});
	}
	
	protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
		outState.putSerializable("registry", _registry);
		outState.putSerializable("media", _media);
		outState.putInt("backgroundId", _backgroundId);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Intent intent = new Intent();
		if(data != null) {
			switch(data.getIntExtra("launch_code", -1)) {
			case CONTACTS:
				intent.setClass(this, ContactsActivity.class);
				startActivityForResult(intent, CONTACTS);
				break;
			case MAP:
				intent.setClass(this, MapActivity.class);
				if(requestCode == MAP_SETTINGS) {
					_backgroundId = data.getIntExtra("backgroundId", R.drawable.forest);
					intent.putExtra("backgroundId", _backgroundId);
				}
				else {
					intent.putExtra("backgroundId", data.getIntExtra("backgroundId", R.drawable.forest));
				}
				startActivityForResult(intent, MAP);
				break;
			case CATALOG:
				intent.setClass(this, CatalogActivity.class);
				intent.putExtra("registry", _registry);
				intent.putExtra("media", _media);
				startActivityForResult(intent, CATALOG);
				break;
			case MULTIMEDIA:
				intent.setClass(this, MultimediaActivity.class);
				intent.putExtra("media", _media);
				startActivityForResult(intent, MULTIMEDIA);
				break;
			case CAMERA:
				intent.setClass(this, CameraActivity.class);
				intent.putExtra("media", _media);
				startActivityForResult(intent, CAMERA);
				break;
			case LASER:
				intent.setClass(this, LaserActivity.class);
				startActivityForResult(intent, LASER);
				break;
			case MAP_SETTINGS:
				intent.setClass(this, SettingsActivity.class);
				intent.putExtra("backgroundId", data.getIntExtra("backgroundId", R.drawable.forest));
				startActivityForResult(intent, MAP_SETTINGS);
				break;
			case INFO:
				intent.putExtra("info", data.getStringExtra("info"));
				intent.putExtra("image", data.getIntExtra("image", -1));
				intent.putExtra("name", data.getStringExtra("name"));
				intent.setClass(this, InfoActivity.class);
				startActivityForResult(intent, INFO);
				break;
			}
			switch(data.getIntExtra("flag", -1)) {
			case POISON_ARROW_FROG:
				intent.putExtra("flag", data.getIntExtra("flag", -1));
				intent.setClass(this, InfoActivity.class);
				startActivityForResult(intent, POISON_ARROW_FROG);
				break;
			case CORUNDUM:
				intent.putExtra("flag", data.getIntExtra("flag", -1));
				intent.setClass(this, InfoActivity.class);
				startActivityForResult(intent, CORUNDUM);
				break;
			}
			switch(requestCode) {
			case CATALOG:
				_registry = (Registry) data.getSerializableExtra("registry");
				break;
			case CAMERA:
				_media = (MediaStorage) data.getSerializableExtra("media");
				break;
			case MULTIMEDIA:
				_media = (MediaStorage) data.getSerializableExtra("media");
				break;
			}
		}
	}

}