package io.springbatch.springbatchlecture.batch.rowmapper;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessagingApiMapper implements RowMapper<ApiRequestVO> {
    @Override
    public ApiRequestVO mapRow(ResultSet rs, int i) throws SQLException {
        return ApiRequestVO.builder()
                .id(rs.getLong("id"))
                .ci(rs.getString("ci"))
                .daysToExpiration(rs.getLong("daysToExpiration"))
                .documentId(rs.getLong("documentId"))
                .documentType(rs.getString("documentType"))
                .status(rs.getString("status"))
                .build();
    }
}
