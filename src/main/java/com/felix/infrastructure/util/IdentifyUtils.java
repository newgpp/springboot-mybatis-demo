package com.felix.infrastructure.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author wenyt 完善计数器线程安全性访问
 */
public class IdentifyUtils {

	private static final int LOCAL = ((getLocal() & 0xff) << 12);

	public static final long MIN_ID = 1555247923200000000L;

	private static final int RAW_OFFSET = TimeZone.getDefault().getRawOffset();

	private static Random RANDOM = new Random(System.nanoTime());

	private static AtomicInteger sequence = new AtomicInteger(0);

	public static long getId() {
		return getIdByTimestamp(System.currentTimeMillis());
	}

	public static long getIdByDate(int date) {
		return getIdByTimestamp(date * 86400000L + RANDOM.nextInt(86400000) - RAW_OFFSET);
	}

	public static long getIdByTime(int time) {
		return getIdByTimestamp(time * 1000L + RANDOM.nextInt(1000));
	}

	public static long getIdByTimestamp(long timestamp) {
		return ((timestamp << 20) | LOCAL | (sequence.incrementAndGet() & 0xfff));
	}

	public static long getTimestampById(long id) {
		return (id >> 20);
	}

	public static int getDateById(long id) {
		return (int) (((id >> 20) + RAW_OFFSET) / 86400000);
	}

	public static long getTimeById(long id) {
		return (id >> 20) / 1000;
	}

	private static byte getLocal() {
		try {
			byte[] addr = InetAddress.getLocalHost().getAddress();
			if (addr != null && addr.length > 0) {
				byte ip = 0;
				for (byte d : addr) {
					ip ^= d;
				}
				return ip;
			}
		} catch (UnknownHostException e) {
		}
		return (byte) new Random(System.nanoTime()).nextInt();
	}
}
