package ist.leic.ipm.dicorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

public class ContactsSearchActivity extends Activity {
	
	private Spinner _distanceSpinner;
	private ListView _searchedContactsList;
	private ImageView _helpButton;
	private String _currentContact;
	private ArrayList<String> _contacts;
	private ArrayList<String> _activeContacts;
	private ArrayList<String> _newContacts;
	private ArrayAdapter<String> _adapter;
	static final int DIALOG_CONTACT_OPTIONS = 0;
	static final int HELP_DIALOG = 1;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contacts_search_layout);
		_contacts = getIntent().getExtras().getStringArrayList("contacts");
		_activeContacts = new ArrayList<String>();
    	String[] newContacts = getResources().getStringArray(R.array.contacts_array);
    	_newContacts = new ArrayList<String>(Arrays.asList(newContacts));
		/*Spinner setup*/
		_distanceSpinner = (Spinner) findViewById(R.id.distance_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.distance_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    _distanceSpinner.setAdapter(adapter);
	    _distanceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				switch(pos) {
				case 0:
					_activeContacts = new ArrayList<String>(_newContacts);
					for(int i = 1; i < _activeContacts.size();) {
						_activeContacts.remove(i);
					}
					break;
				case 1:
					_activeContacts = new ArrayList<String>(_newContacts);
					for(int i = 2; i < _activeContacts.size();) {
						_activeContacts.remove(i);
					}
					break;
				case 2:
					_activeContacts = new ArrayList<String>(_newContacts);
					for(int i = 3; i < _activeContacts.size();) {
						_activeContacts.remove(i);
					}					
					break;
				case 3:
					_activeContacts = new ArrayList<String>(_newContacts);
					for(int i = 4; i < _activeContacts.size();) {
						_activeContacts.remove(i);
					}					
					break;
				case 4:
					_activeContacts = new ArrayList<String>(_newContacts);					
					break;
				default:
					break;
				}
			    _adapter = new ArrayAdapter<String>(ContactsSearchActivity.this, R.layout.list_item, _activeContacts);
				_searchedContactsList.setAdapter(_adapter);
				_adapter.notifyDataSetChanged();
			}
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	    /*ListView setup*/
		_searchedContactsList = (ListView) findViewById(R.id.contacts_search_list);
		_searchedContactsList.setTextFilterEnabled(true);
		_searchedContactsList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				_currentContact = _activeContacts.get(position);
				showDialog(DIALOG_CONTACT_OPTIONS);
		    }
		});
		/*ImageView setup*/
		_helpButton = (ImageView) findViewById(R.id.help_icon_contacts_search);
		_helpButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(HELP_DIALOG);
			}
		});
	}
	
	protected void onPause() {
		super.onPause();
		Collections.sort(_contacts);
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id){
		case DIALOG_CONTACT_OPTIONS:
			builder.setMessage(_currentContact);
			builder.setPositiveButton(getString(R.string.call), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Intent intent = new Intent();
					intent.setClass(ContactsSearchActivity.this, CallActivity.class);
					intent.putExtra("contact", _currentContact);
					dialog.cancel();
					ContactsSearchActivity.this.startActivity(intent);					
				}
			});
			builder.setNeutralButton(getString(R.string.save), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					if(!_contacts.contains(_currentContact)) {
						_contacts.add(_currentContact);
					}
				}
			});
			builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();					
				}
			});
			dialog = builder.create();
			break;
		case HELP_DIALOG:
			String helpMessage = getString(R.string.contact_search_help);
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
		case DIALOG_CONTACT_OPTIONS:
			((AlertDialog) dialog).setMessage(_currentContact);
			break;
		default:
			break;
		}
	}

}