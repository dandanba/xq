package com.xq.main.app;

import java.util.concurrent.ConcurrentHashMap;

public class Messager {
	private final ConcurrentHashMap<String, Object> mMessages = new ConcurrentHashMap<String, Object>();

	public boolean containsKey(String key) {
		return mMessages.containsKey(key);
	}

	public Object get(String key) {
		return mMessages.get(key);
	}

	public void put(String key, Object value) {
		mMessages.put(key, value);
	}

	public void remove(String key) {
		mMessages.remove(key);
	}
}
