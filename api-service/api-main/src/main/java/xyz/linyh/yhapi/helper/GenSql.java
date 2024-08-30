package xyz.linyh.yhapi.helper;

import org.apache.commons.lang3.StringUtils;
import xyz.linyh.model.datasource.entitys.DscInfo;
import xyz.linyh.model.datasource.vos.ColumnBriefVO;

import java.util.Arrays;
import java.util.List;

public abstract class GenSql {
    public String createSql(DscInfo dscInfo, List<ColumnBriefVO> searchColumns) {
        ColumnBriefVO columnBriefVO = searchColumns.get(0);
//        TODO 表名加上日期
        String resultTableName = "abc";
//        1. 创建删除表语句
        String dropTableSql = createDropTableSql(resultTableName);

//        2. 创建查询语句
        String selectSql = createSelectSql(searchColumns);

//        3. 创建建表语句
        String createTableSql = createCreateTableSql(resultTableName, searchColumns);

//        4. 创建插入表语句
        String insertSql = createInsertSql(resultTableName, selectSql);

//        5. 将所有sql语句合并到一起通过分号分割返回
        List<String> sqlList = Arrays.asList(dropTableSql, createTableSql, insertSql);
        return StringUtils.join(sqlList, ";");
    }

    protected abstract String createDropTableSql(String tableName);

    protected abstract String createSelectSql(List<ColumnBriefVO> columns);

    protected abstract String createCreateTableSql(String tableName, List<ColumnBriefVO> columns);

    protected abstract String createInsertSql(String tableName, String selectSql);
}
