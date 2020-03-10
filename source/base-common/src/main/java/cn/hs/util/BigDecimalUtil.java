package cn.hs.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

	/**
	 * 返回两位小数点(截取，不进行四舍五入)
	 * @param v
	 * @return
	 */
	public static BigDecimal to2PointScaleDown(BigDecimal v) {
		if (v == null) {
			return new BigDecimal(0.00);
		}
		return v.setScale(2, BigDecimal.ROUND_DOWN);
	}

	/**
	 * 返回两位小数点
	 * @param d
	 * @return
	 */
	public static BigDecimal to2Point(double d) {
		BigDecimal v = new BigDecimal(d);
		return v.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 返回两位小数点
	 * @param v
	 * @return
	 */
	public static BigDecimal to2Point(BigDecimal v) {
		if (v == null) {
			return new BigDecimal(0.00);
		}
		return v.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 返回五位小数点
	 * @param v
	 * @return
	 */
	public static BigDecimal to5Point(BigDecimal v) {
		if (v == null) {
			return new BigDecimal(0.00000);
		}
		return v.setScale(5, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 返回8位小数点
	 * @param v
	 * @return
	 */
	public static BigDecimal to8Point(BigDecimal v) {
		if (v == null) {
			return new BigDecimal(0.00000000);
		}
		return v.setScale(8, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 返回8位小数点
	 * @param d
	 * @return
	 */
	public static BigDecimal to8Point(double d) {
		BigDecimal v = new BigDecimal(d);
		return v.setScale(8, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 相加
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		if(null == v1) {
			v1 = BigDecimal.ZERO;
		}
		if(null == v2) {
			v2 = BigDecimal.ZERO;
		}
		return v1.add(v2);
	}

	/**
	 * 相加
	 * @param v
	 * @return
	 */
	public static BigDecimal adds(BigDecimal... v) {
		int len = v.length;
		BigDecimal result = null;
		for (int i = 0; i < len; i++) {
			result = add(result, v[i]);
		}
		return result;
	}

	/**
	 * 减少并取绝对值
	 * @param v1	被减数
	 * @param v2	减数
	 * @return		差，并取绝对值
	 */
	public static BigDecimal subtractAndAbs(BigDecimal v1, BigDecimal v2) {
		if(null == v1) {
			v1 = BigDecimal.ZERO;
		}
		if(null == v2) {
			v2 = BigDecimal.ZERO;
		}
		return v1.subtract(v2).abs();
	}

	/**
	 * 减少
	 * @param v1	被减数
	 * @param v2	减数
	 * @return		差
	 */
	public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {
		if(null == v1) {
			v1 = BigDecimal.ZERO;
		}
		if(null == v2) {
			v2 = BigDecimal.ZERO;
		}
		return v1.subtract(v2);
	}

	/**
	 * 相乘
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
        if(null == v1) {
            v1 = BigDecimal.ZERO;
        }
        if(null == v2) {
            v2 = BigDecimal.ZERO;
        }
		return v1.multiply(v2);
	}

	/**
	 * 判断是否为null或者0
	 * @param v
	 * @return false:null或0，true其他值
	 */
	public static Boolean isZeroOrNull(BigDecimal v) {
		if (null == v) {
			return true;
		}
		if(BigDecimal.ZERO.compareTo(v) == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {//
		// System.out.println(add(null, null));
		// System.out.println(subtract(new BigDecimal(1L), null));
		// System.out.println(subtract(null, new BigDecimal(2L)));
		// System.out.println(subtract(new BigDecimal(1L), new BigDecimal(4L)));
		System.out.println(adds(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)));
	}
}
