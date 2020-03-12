package cn.hs.ant.bean;


/**
 * 元数据
 * @author swt
 */
public class MetaData implements java.io.Serializable {

	/** Lable */
	private String columnLabel;
	/** Name */
	private String columnName;
	/** type */
	private int columnType;
	/** type name */
	private String columnTypeName;
	/** value */
	private Object columnValue;

	public Object getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(Object columnValue) {
		this.columnValue = columnValue;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getColumnType() {
		return columnType;
	}

	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
}
