package ist.leic.ipm.dicorder;

import java.io.Serializable;
import java.util.HashMap;

public class Registry implements Serializable {
	
	private HashMap<String, String> _infos = new HashMap<String, String>();
	private HashMap<String, Integer> _images = new HashMap<String, Integer>();
	
	public boolean addInfo(String name, String info) {
		if(_infos.containsKey(name)) {
			return false;
		}
		_infos.put(name, info);
		return true;
	}
	
	public boolean addImage(String name, int image) {
		if(_images.containsKey(name)) {
			return false;
		}
		_images.put(name, new Integer(image));
		return true;
	}
	
	public boolean exists(String name) {
		return _infos.containsKey(name) || _images.containsKey(name);
	}
	
	public String getInfo(String name) {
		return _infos.get(name);
	}
	
	public int getImage(String name) {
		if(_images.containsKey(name)) {
			return (_images.get(name)).intValue();
		}
		return -1;
	}

}
