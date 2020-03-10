package cn.hs.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * 点定位常用函数
 * @author swt
 */
public class PointPositionUtil {
    /** 单位 m地球半径 */
    public static double EARTH_RADIUS = 6378137.00;

    public static void main(String[] args) {
        //Point2D.Double point = new Point2D.Double(116.396, 39.921);

        // 当前的点集合（测试）
//        List<Point2D.Double> points = new ArrayList<>();
//        points.add(new Point2D.Double(20.13, 30.14));
//        points.add(new Point2D.Double(20.26, 70.165));
//        points.add(new Point2D.Double(10.36, 40.846));
//        points.add(new Point2D.Double(40.28, 10.69));
//        points.add(new Point2D.Double(50.54, 20.843));
//        points.add(new Point2D.Double(50.136, 30.364));
//
//        // 多边形
//        List<Point2D.Double> polygon = new ArrayList<>();
//        polygon.add(new Point2D.Double(10.158, 20.741));
//        polygon.add(new Point2D.Double(20.284, 40.788));
//        polygon.add(new Point2D.Double(40.365, 50.645));
//        polygon.add(new Point2D.Double(60.258, 30.321));
//        polygon.add(new Point2D.Double(50.147, 10.753));

        /*long bTime = System.currentTimeMillis();
        for (int k = 0; k < 10000; k++) {
            for (int i = 0; i < points.size(); i++) {
                if (isPtInPoly(points.get(i), polygon)) {
                    System.out.println("点在多边形内");
                } else {
                    System.out.println("点在多边形外");
                }
            }
        }
        System.out.println(System.currentTimeMillis() - bTime);*/
    }

    /**
     * 经纬度转化为弧度
     * @param d 传入经度或者纬度坐标
     * @return  弧度
     */
    private static double rad(double d){
        return d * Math.PI/180.0;
    }

    /**
     * 计算两个经纬度之间距离
     * @param x1    开始经纬
     * @param y1    开始维度
     * @param x2    结束经纬
     * @param y2    结束经纬
     * @return      距离
     */
    public static double getDistance(double x1, double y1, double x2, double y2){
        double radlog1 = rad(x1);
        double radlog2 = rad(x2);
        double radlat1  = rad(y1);
        double radlat2 = rad(y2);

        double a = radlog1 - radlog2;//经度间距
        double b = radlat1 - radlat2;//纬度间距
        double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(b/2), 2)
                + Math.cos(radlat1) * Math.cos(radlat2) * Math.pow(Math.sin(a/2), 2)));

        s = s * EARTH_RADIUS;
        s = Math.round(s*10000)/10000;
        return s;

    }

    /**
     * 判断点是否在多边形内
     * @param point 检测点
     * @param polygon 多边形的顶点
     * @return 点在多边形内返回true,否则返回false
     */
    public static boolean isPtInPoly(Point2D.Double point, List<Point2D.Double> polygon) {
        int N = polygon.size();
        boolean boundOrVertex = true; // 如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;// 点穿过后与边界的交点个数
        double precision = 2e-10; // 浮点类型计算时候与0比较时候的容差
        Point2D.Double p1, p2;// 当前点位置的多边形左右两个点
        Point2D.Double p = point; // 当前点

        p1 = polygon.get(0);//
        for (int i = 1; i <= N; ++i) {// 遍历所有多边形的点
            if (p.equals(p1)) {
                return boundOrVertex;// 点在边界点上
            }

            p2 = polygon.get(i % N);// 右边界点
            if (p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)) {
                p1 = p2;
                continue;// 右边界点赋值给左边界点，新的左边界点
            }

            if (p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)) {
                if (p.y <= Math.max(p1.y, p2.y)) {
                    if (p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)) { // 左右两个点x相同，当前点的y在p1.p2线上（垂直情况）
                        return boundOrVertex;
                    }
                    if (p1.y == p2.y) {
                        if (p1.y == p.y) {
                            return boundOrVertex;
                        } else {
                            ++intersectCount;
                        }
                    } else {
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;
                        if (Math.abs(p.y - xinters) < precision) {
                            return boundOrVertex;
                        }

                        if (p.y < xinters) {
                            ++intersectCount;
                        }
                    }
                }
            } else {
                if (p.x == p2.x && p.y <= p2.y) {
                    Point2D.Double p3 = polygon.get((i + 1) % N);
                    if (p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)) {
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;
        }

        if (intersectCount % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

}
