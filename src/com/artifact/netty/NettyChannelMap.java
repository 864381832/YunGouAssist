package com.artifact.netty;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by yaozb on 15-4-11.
 */
public class NettyChannelMap {
	private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

	public static void add(String clientId, SocketChannel socketChannel) {
		map.put(clientId, socketChannel);
	}

	public static SocketChannel get(String clientId) {
		return map.get(clientId);
	}

	public static void remove(SocketChannel socketChannel) {
		for (Entry<String, SocketChannel> entry : map.entrySet()) {
			if (entry.getValue() == socketChannel) {
				map.remove(entry.getKey());
			}
		}
	}

}
