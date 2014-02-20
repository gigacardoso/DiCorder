package ist.leic.ipm.dicorder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class MediaStorage implements Serializable {

	private TreeMap<String, Integer> _pictures = new TreeMap<String, Integer>();
	private TreeMap<String, Integer> _videos = new TreeMap<String, Integer>();
	private ArrayList<String> _audio = new ArrayList<String>();
	
	public boolean addPicture(String name, int picture) {
		if(_pictures.containsKey(name)) {
			return false;
		}
		_pictures.put(name, new Integer(picture));
		return true;
	}
	
	public boolean addVideo(String name, int video) {
		if(_videos.containsKey(name)) {
			return false;
		}
		_videos.put(name, new Integer(video));
		return true;
	}
	
	public void removePicture(String name) {
		_pictures.remove(name);
	}

	
	public void removeVideo(String name) {
		_videos.remove(name);
	}
	
	public int getPicture(String name) {
		return _pictures.get(name).intValue();
	}
	
	public int getVideo(String name) {
		return _videos.get(name).intValue();
	}
	
	public ArrayList<String> getPicturesNames() {
		return new ArrayList<String>(_pictures.keySet());
	}
	
	public ArrayList<String> getVideosNames() {
		return new ArrayList<String>(_videos.keySet());
	}
	
	public ArrayList<Integer> getPictures() {
		return new ArrayList<Integer>(_pictures.values());
	}
	
	public ArrayList<Integer> getVideos() {
		return new ArrayList<Integer>(_videos.values());
	}
	
	public boolean addAudio(String name) {
		if(_audio.contains(name)) {
			return false;
		}
		_audio.add(name);
		return true;
	}
	
	public void addAudio(int n) {
		for(int i = 0; i < n; ++i) {
			_audio.add("Audio File " + i);
		}
	}
	
	public void removeAudio(int n) {
		_audio.remove(n);
	}
	
	public ArrayList<String> getAudio() {
		return _audio;
	}
	
}