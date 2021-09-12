package io.springbatch.springbatchlecture.batch.rowmapper;

import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SendApiRowMapper implements RowMapper<ApiRequestVO> {
    @Override
    public ApiRequestVO mapRow(ResultSet rs, int i) throws SQLException {
        return ApiRequestVO.builder()
                .ifYmd(rs.getString("IF_YMD"))
                .ifSeq(rs.getInt("IF_SEQ"))
                .ci(rs.getString("CI"))
                .name(rs.getString("NAME"))
                .ifPrcsSttus(rs.getString("IF_PRCS_STTUS"))
                .msgGubun(rs.getString("MSND.MSG_GUBUN"))
                .formGubun(rs.getString("FORM_GUBUN"))
                .dataTrsmYmd(rs.getString("DATA_TRSM_YMD"))
                .rrnoAtlnHighVal(rs.getString("RRNO_ATLN_HIGH_VAL"))
                .edycNo(rs.getString("EDYC_NO"))
                .keyYmd(rs.getString("KEY_YMD"))
                .msgSeq(rs.getString("MSG_SEQ"))
                .redirectUrl(rs.getString("REDIRECT_URL"))
                .actYn(rs.getString("ACT_YN"))
                .build();
    }
}
