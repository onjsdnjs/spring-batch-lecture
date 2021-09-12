package io.springbatch.springbatchlecture.batch.job.send;

import io.springbatch.springbatchlecture.batch.domain.MessageVO;
import io.springbatch.springbatchlecture.batch.rowmapper.MsgRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * io.anymobi.core.batch.job.send
 * ㄴ SendDataChildJobConfiguration.java
 * </pre>
 * 쿼리 생성 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:34
 * @see :
 **/

public class QueryGenerator {

    public static MessageVO[] getMsgList(DataSource dataSource) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<MessageVO> msgList = jdbcTemplate.query(getSelectClauseQueryForMsg(), new MsgRowMapper<>());

        return msgList.toArray(new MessageVO[]{});
    }

    public static String getSelectClauseQueryForMsg() {
        return "SELECT * FROM MESSAGE_TEMPLATE WHERE USEYN = 'Y'";
    }

    public static String getSelectClauseQueryForTarget() {

        return "IF_YMD, IF_SEQ, CI, NAME, IF_PRCS_STTUS\n" +
                ",MSND.MSG_GUBUN\n" +
                ",(SELECT CM_CD_NM FROM TB_CM_CD WHERE CM_CD_GRP_ID = 'FORM_CM_CD' AND CM_CD = MSND.FORM_TYPE) AS FORM_GUBUN\n" +
                ",DATA_TRSM_YMD\n" +
                ",RRNO_ATLN_HIGH_VAL\n" +
                ",EDYC_NO\n" +
                ",KEY_YMD\n" +
                ",TKM.MSG_SEQ , TKM.URL AS REDIRECT_URL\n" +
                ",(SELECT ACT_YN FROM TB_KT_TERMS WHERE CI = MSND.CI) AS ACT_YN";
    }

    public static String getSelectQueryForTarget() {

        return "ID, CI, DAYSTOEXPIRATION, DOCUMENTID, DOCUMENTTYPE, STATUS";
    }

    public static String getFromClauseQueryForTarget() {

        return "FROM IFR_KT_MSND MSND\n" +
                "JOIN TB_KT_MSG TKM\n" +
                "ON MSND.MSG_GUBUN = TKM.MSG_GUBUN\n" +
                "AND TKM.USE_YN = 'Y'\n" +
                "AND TKM.APPROVAL_YN = 'Y'";
    }

    public static String getFromQueryForTarget() {

        return "FROM TO_BE_SEND_KT";
    }

    public static String getWhereClauseQueryForTarget() {

        return "WHERE IF_PRCS_STTUS='N'\n" +
                "AND TKM.MSG_SEQ = CAST(:msg_seq AS INT)\n" +
                "AND MSND.IF_YMD <= DATE_FORMAT(NOW(), '%Y%m%d')";

    }

    public static String getWhereQueryForTarget() {

        return "WHERE STATUS = 'NOT_YET'\n" +
                "AND documentType = :str";

    }

    public static Map<String, Object> getParameterForQuery(String parameter, int value) {

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(parameter, value);
        return parameters;
    }

    public static Map<String, Object> getParameterForQuery(String parameter, String value) {

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(parameter, value);
        return parameters;
    }
}
