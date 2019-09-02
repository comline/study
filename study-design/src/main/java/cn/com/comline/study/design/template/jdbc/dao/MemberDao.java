package cn.com.comline.study.design.template.jdbc.dao;

import cn.com.comline.study.design.template.jdbc.JdbcTemplate;
import cn.com.comline.study.design.template.jdbc.Member;
import cn.com.comline.study.design.template.jdbc.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class MemberDao extends JdbcTemplate {
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll(){
        String sql = "select * from t_member";
        return super.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet ts, int rowNum) throws Exception {
                Member member = new Member();
                member.setUsername(ts.getString("username"));
                member.setPassword(ts.getString("password"));
                member.setAge(ts.getInt("age"));
                member.setAddr(ts.getString("addr"));
                return member;
            }
        },null);
    }
}
