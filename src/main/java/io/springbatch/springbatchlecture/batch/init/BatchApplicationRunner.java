package io.springbatch.springbatchlecture.batch.init;

import io.anymobi.domain.vo.Constants;
import io.anymobi.domain.vo.SharedObject;
import io.anymobi.service.data.ChkDataService;
import io.anymobi.service.data.SendDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class BatchApplicationRunner implements ApplicationRunner {

    private final SharedObject sharedObject;
    private final Job dataSendJob;
    private final Job dataChkJob;
    private final SendDataService dataSenderService;
    private final ChkDataService chkDataService;
    private final DataSource primaryDataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        sharedObject.setSharedObject(Constants.JOB_NAME.SEND_JOB, dataSendJob);
        sharedObject.setSharedObject(Constants.TASK_CD.TASK_DATA_SND, dataSenderService);

        sharedObject.setSharedObject(Constants.JOB_NAME.CHK_JOB, dataChkJob);
        sharedObject.setSharedObject(Constants.TASK_CD.TASK_DATA_CHK, chkDataService);

    }
}
