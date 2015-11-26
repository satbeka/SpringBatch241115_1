package kz.springappsat.batch.tasklet;

import db1.TblSys;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class CloneTblToTbl2Tasklet implements Tasklet, InitializingBean {


	private String tblName;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(tblName, "tblName must be set");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {


		boolean cloned = TblSys.execute();
		if (!cloned) {
			throw new UnexpectedJobExecutionException("Could not clone Tbl=" + tblName);
		} else {
			System.out.println(tblName + " is cloned!");
		}
		return RepeatStatus.FINISHED;

	}





	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	public String getTblName() {
		return tblName;
	}
}