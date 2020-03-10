package cn.hs.util;

/**
 * 点定位常用函数
 * @author swt
 */
public class DirectionUtil {

    public static void main(String[] args) {
//        double navigateDirection = 195.2;   // 航行角度
//        double shipDirection = 284.5;			//船的角度
//        System.out.println(direction(navigateDirection, shipDirection));
    }

    /**
     * 判断航向是否正确
     * @param arrows    航行角度（上行角度）
     * @param ship      船头向
     */
    public static boolean direction(double arrows, double ship) {
        double left;
        double right;
        left = arrows + 90.0;
        right = arrows + 270.0;
        double finalpoint = 360.0;
        if (arrows < 90) {
            if ((ship >= arrows) && (ship <= left) || (ship >= right) && (ship <= finalpoint)
                    || (ship >= 0) && (ship <= right + 90 - finalpoint)) {
                return true;
            }
        } else if (arrows > 270) {
            if ((ship >= arrows - 90) && (ship <= arrows) || (ship >= arrows) && (ship <= finalpoint)
                    || (ship >= 0) && (ship <= left - finalpoint)) {
                return true;
            }
        } else {
            if ((ship >= arrows - 90) && (ship <= arrows) || (ship >= arrows) && (ship <= left)) {
                return true;
            }
        }
        return false;
    }

}
