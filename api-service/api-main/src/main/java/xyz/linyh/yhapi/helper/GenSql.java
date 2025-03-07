package xyz.linyh.yhapi.helper;

import org.apache.commons.lang3.StringUtils;
import xyz.linyh.model.datasource.entitys.DscInfo;
import xyz.linyh.model.dscInterfaceColumn.entitys.DscInterfaceColumn;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class GenSql {

    public static String TABLE_NAME_FIX = "yh_api";

    public String createSql(DscInfo dscInfo, List<DscInterfaceColumn> dscInterfaceColumns) {
        String schemaName = dscInfo.getSchemaName();
        DscInterfaceColumn dscInterfaceColumn = dscInterfaceColumns.get(0);
        String tableName = dscInterfaceColumn.getTableName();
        String nowDate = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String resultTableName = String.format("%s.%s", schemaName, tableName + TABLE_NAME_FIX + "_" + nowDate);

//        1. 创建删除表语句
        String dropTableSql = createDropTableSql(resultTableName);

//        3. 创建建表语句
        String createTableSql = createCreateTableSql(resultTableName, dscInterfaceColumns);

//        表名和字段名增加别名
//        4. 创建插入表语句
        String insertSql = createInsertSql(resultTableName, dscInterfaceColumns);

//        5. 将所有sql语句合并到一起通过分号分割返回
        List<String> sqlList = Arrays.asList(dropTableSql, createTableSql, insertSql);
        return StringUtils.join(sqlList, ";");
    }

    protected abstract String createDropTableSql(String tableName);

    protected abstract String createSelectSqlWithPtAndPk(List<DscInterfaceColumn> columns);

    protected abstract String createCreateTableSql(String tableName, List<DscInterfaceColumn> columns);

    protected abstract String createInsertSql(String tableName, List<DscInterfaceColumn> columns);

    public abstract String renameTable(String srcTableName, String destTableName);
}
