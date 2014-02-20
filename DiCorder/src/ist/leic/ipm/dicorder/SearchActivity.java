package ist.leic.ipm.dicorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchActivity extends Activity {
	
	static final int INFO = 7;
	static final int HELP_DIALOG = 0;
	
	private Registry _registry;
	private EditText _searchField;
	private TextView _searchResult;
	private String _currentName;
	private Button _info;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		
		_registry = (Registry) getIntent().getSerializableExtra("registry");
		
		_searchResult = (TextView) findViewById(R.id.search_result);
		
		_searchField = (EditText) findViewById(R.id.search_field);
		_searchField.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					if(_registry.exists(_searchField.getText().toString())) {
						_currentName = _searchField.getText().toString();
						_searchResult.setText(_currentName);
						LinearLayout ll = (LinearLayout) SearchActivity.this.findViewById(R.id.sl2);
						ll.removeView(_info);
						_info = new Button(SearchActivity.this);
						_info.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Intent intent = SearchActivity.this.getIntent();
								intent.putExtra("info", _registry.getInfo(_currentName));
								intent.putExtra("image", _registry.getImage(_currentName));
								intent.putExtra("name", _currentName);
								intent.putExtra("launch_code", INFO);
								SearchActivity.this.finish();
							}
						});
						_info.setText(getResources().getString(R.string.info));
						ll.addView(_info);
					}
				}
				return true;
			}
		});
		
		Button search = (Button) findViewById(R.id.search_button);
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(_registry.exists(_searchField.getText().toString())) {
					_currentName = _searchField.getText().toString();
					_searchResult.setText(_searchField.getText().toString());
					LinearLayout ll = (LinearLayout) SearchActivity.this.findViewById(R.id.sl2);
					ll.removeView(_info);
					_info = new Button(SearchActivity.this);
					_info.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							Intent intent = SearchActivity.this.getIntent();
							intent.putExtra("info", _registry.getInfo(_currentName));
							intent.putExtra("image", _registry.getImage(_currentName));
							intent.putExtra("name", _currentName);
							intent.putExtra("launch_code", INFO);
							SearchActivity.this.finish();
						}
					});
					_info.setText(getResources().getString(R.string.info));
					ll.addView(_info);					
				}
			}
		});
		
		ImageView help = (ImageView) findViewById(R.id.search_help_icon);
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
			String helpMessage = getString(R.string.search_help);
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