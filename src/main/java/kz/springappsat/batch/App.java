package kz.springappsat.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static Job job;
    public static JobLauncher jobLauncher;
    public static JobRepository jobRepository;

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public static void main(String[] args) {

        App obj = new App();
        obj.run();

    }

    private void run() {

        String[] springConfig = { "file:src/main/resources/job-read-files.xml" };

        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("myJob");

        try {

            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Exit Status : " + execution.getStatus());
            System.out.println("Exit Status : " + execution.getAllFailureExceptions());

        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("Done");

    }

}

