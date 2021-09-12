package io.springbatch.springbatchlecture.batch.rowmapper;

import io.springbatch.springbatchlecture.batch.domain.MessageVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MsgRowMapper<T> implements RowMapper<T> {
    @Override
    public T mapRow(ResultSet rs, int i) throws SQLException {
        return (T) MessageVO.builder()
                .messageTemplateName(rs.getString("message_template_name"))
                .messageTemplateId(rs.getLong("message_template_id"))
                .messageTemplateContent(rs.getString("message_template_content"))
                .messageTemplateTitle(rs.getString("message_template_title"))
                .useYn(rs.getString("useYn"))
                .build();
    }
}
