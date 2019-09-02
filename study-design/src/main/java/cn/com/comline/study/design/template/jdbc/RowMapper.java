package cn.com.comline.study.design.template.jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化接口
 * @param <T>
 */
public interface RowMapper<T> {
    T mapRow(ResultSet ts,int rowNum) throws Exception;
}
