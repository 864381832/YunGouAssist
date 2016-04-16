package com.artifact.global;

import java.util.concurrent.ConcurrentHashMap;

import com.artifact.model.User;

public class CacheSelectData {
	private static ConcurrentHashMap<String, User> userCache = new ConcurrentHashMap<String, User>();

	public static User getUserCache(String key) {
		return userCache.get(key);
	}

	public static void addtUserCache(String key, User user) {
		CacheSelectData.userCache.put(key, user);
	}

	public static void removeUserCache(String key) {
		CacheSelectData.userCache.remove(key);
	}

}
