package cn.hs.bean.base;


import java.awt.geom.Point2D;
import java.util.List;

/**
 * 绘制多边形
 * @author yaotailin
 */
public class DrawPolygon implements java.io.Serializable {

	/** 画笔颜色值 */
	private String brushColor;
	/** 填充颜色值 */
	private String fillColor;
	/** 填充颜色值 */
	private Integer brushThickness;
	/** 点列表 */
	private List<Point2D.Double> pointList;

	public String getBrushColor() {
		return brushColor;
	}

	public void setBrushColor(String brushColor) {
		this.brushColor = brushColor;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public Integer getBrushThickness() {
		return brushThickness;
	}

	public void setBrushThickness(Integer brushThickness) {
		this.brushThickness = brushThickness;
	}

	public List<Point2D.Double> getPointList() {
		return pointList;
	}

	public void setPointList(List<Point2D.Double> pointList) {
		this.pointList = pointList;
	}
}
