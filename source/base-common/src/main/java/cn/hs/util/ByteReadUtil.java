package cn.hs.util;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteReadUtil {

	/**
	 * 将byte[]数组转为String
	 * @param val 待转换byte数组，
	 * @param startpoint 待转换byte数组开始位置
	 * @param maxLen 待转换byte数组长度
	 * @return
	 */
	public static String toString(byte[] val, int startpoint, int maxLen) {
		int index = 0;
		while(index + startpoint < val.length && index < maxLen) {
			if(val[index+startpoint] == 0) {
				break;
			}
			index++;
		}
		byte[] temp = new byte[index];
		System.arraycopy(val, startpoint, temp, 0, index);
		return new String(temp);
	}

	/**
	 * 低位转高位
	 * @param littleEndian	低位
	 * @return				高位
	 */
	public static int toInt(int littleEndian) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.asIntBuffer().put(littleEndian);
		buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getInt();
	}

	/**
	 * 低位转高位
	 * @param littleEndian	低位
	 * @return				高位
	 */
	public static int toShort(int littleEndian) {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.asShortBuffer().put((short)littleEndian);
		buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getShort();
	}

	/**
	 * 低位转高位
	 * @param littleEndian	低位
	 * @return				高位
	 */
	public static int toChar(char littleEndian) {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.asCharBuffer().put(littleEndian);
		buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getChar();
	}

	/**
	 * 低位转高位
	 * @param littleEndian	低位
	 * @return				高位
	 */
	public static double toDouble(double littleEndian) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.asDoubleBuffer().put(littleEndian);
		buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getDouble();
	}

}
