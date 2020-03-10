package cn.hs.config;

import cn.hs.util.UF;

import static java.sql.Types.*;

/**
 * 线程池配置
 * @author songwentao
 */
public class SQLTypesUtils {

    /**
     * 获取数据类型
     * @param columnType
     * @return
     */
    public static int getColumnType(String columnType) {
        columnType = UF.toString(columnType).toUpperCase();
        switch (columnType) {
            case "BIT":{
                return BIT;
            }
            case "TINYINT":{
                return TINYINT;
            }
            case "SMALLINT":{
                return SMALLINT;
            }
            case "INTEGER":{
                return INTEGER;
            }
            case "BIGINT":{
                return BIGINT;
            }
            case "FLOAT":{
                return FLOAT;
            }
            case "REAL":{
                return REAL;
            }
            case "DOUBLE":{
                return DOUBLE;
            }
            case "NUMERIC":{
                return NUMERIC;
            }
            case "DECIMAL":{
                return DECIMAL;
            }
            case "CHAR":{
                return CHAR;
            }
            case "VARCHAR":{
                return VARCHAR;
            }
            case "LONGVARCHAR":{
                return LONGVARCHAR;
            }
            case "DATE":{
                return DATE;
            }
            case "TIME":{
                return TIME;
            }
            case "TIMESTAMP":{
                return TIMESTAMP;
            }
            case "BINARY":{
                return BINARY;
            }
            case "VARBINARY":{
                return VARBINARY;
            }
            case "LONGVARBINARY":{
                return LONGVARBINARY;
            }
            case "NULL":{
                return NULL;
            }
            case "OTHER":{
                return OTHER;
            }
            case "JAVA_OBJECT":{
                return JAVA_OBJECT;
            }
            case "DISTINCT":{
                return DISTINCT;
            }
            case "STRUCT":{
                return STRUCT;
            }
            case "ARRAY":{
                return ARRAY;
            }
            case "BLOB":{
                return BLOB;
            }
            case "CLOB":{
                return CLOB;
            }
            case "REF":{
                return REF;
            }
            case "DATALINK":{
                return DATALINK;
            }
            case "BOOLEAN":{
                return BOOLEAN;
            }
            case "ROWID":{
                return ROWID;
            }
            case "NCHAR":{
                return NCHAR;
            }
            case "NVARCHAR":{
                return NVARCHAR;
            }
            case "LONGNVARCHAR":{
                return LONGNVARCHAR;
            }
            case "NCLOB":{
                return NCLOB;
            }
            case "SQLXML":{
                return SQLXML;
            }
            case "REF_CURSOR":{
                return REF_CURSOR;
            }
            case "TIME_WITH_TIMEZONE":{
                return TIME_WITH_TIMEZONE;
            }
            case "TIMESTAMP_WITH_TIMEZONE":{
                return TIMESTAMP_WITH_TIMEZONE;
            }
            default:{
                return VARCHAR;
            }
        }

    }

}
