package cn.hs.ant.bean;


/**
 * 元数据
 * @author swt
 */
public class ColumnMapping implements java.io.Serializable {

	/** 列标签 */
	private String columnLabel;
	/** 列类型 */
	private int columnType;
	/** 列类型 */
	private String columnTypeName;
	/** 转换类型 */
	private String transformType;
	/** 来源 */
	private String source;

	public int getColumnType() {
		return columnType;
	}

	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	public String getTransformType() {
		return transformType;
	}

	public void setTransformType(String transformType) {
		this.transformType = transformType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
