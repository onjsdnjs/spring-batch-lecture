package io.springbatch.springbatchlecture.batch.domain;

public class Constants {


    public interface TASK_CD {
        String TASK_REGIST = "KT_REGIST";
        String TASK_DATA_SND = "KT_DATA_SND";
        String TASK_DATA_CHK = "KT_DATA_CHK";
        String TASK_DATA_SET = "KT_DATA_SET";
        String TASK_CI_SET = "KT_CI_SET";

        String TASK_TARGET_SET = "KT_TARGET_SET";

    }

    public interface JOB_NAME {
        String SEND_JOB = "SEND_JOB";
        String CHK_JOB = "CHK_JOB";
        String RECEIVE_JOB = "RECEIVE_JOB";
    }

    public interface API_CD {
        String MOBILE = "MOBILE";
        String MMS = "MMS";
        String TALK = "TALK";
    }

    public interface MSG {
        String MSG_CD = "MSG_CD";
    }

    public static String MSG_LIST = "MSG_LIST";

    public interface KT_ERR_CD {
        String NO_MEMBER = "47";
        String SRC_ERR = "9000";
        String LEN_ERR = "9002";
        String ETC_ERR = "E499";
        String SYS_ERR = "6001";
        String NOT_FOUND = "E498";
        String TIMEOUT = "43";
    }

    public interface CFG_TP_CD {
        String TEST = "TEST";
        String PROD = "PROD";
    }

    public interface TASK_STTUS_CD {
        String INIT_STOP = "T";
        String WAITING = "W";
        String RUNNING = "R";
    }

    public interface TASK_INST_CMD {
        String NONE = "N";
        String RUN = "CR";
        String SHUTDOWN = "CT";
        String RESTART = "RS";
    }

    public interface NAVER_ERR_CD {

        String INVALID_PARAM = "400";
        String INVLID_CLIENT_INFO = "402";
        String FORBIDDEN_USER = "403";
        String NO_MEMBER = "404";
        String SERVER_ERROR = "500";

    }

    public interface SEND_STTUS_CD {
        String SEND = "SEND";
        String ERROR = "SERR";
        String NO_CI = "NOCI";
        String RECEIVE = "RECV";
        String NO_RECEIVE = "NORV";
        String READ = "READ";
        String TIMEOUT = "TMOT";
        String RECEIVE_ERR = "RERR";
    }

    public interface IF_PRCS_STTUS {
        String NONE = "N";
        String SUCCESS = "S";
        String FAIL = "F";
        String ERROR = "E";
    }
}
