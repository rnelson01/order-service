package job;

import com.newrelic.api.agent.Trace;
import org.apache.log4j.Logger;

import java.util.Random;

public class RegistrationBackgroundHandler {
	private final static Logger logger = Logger.getLogger(RegistrationBackgroundHandler.class);
	private final Random random = new Random();

	@Trace(dispatcher=true)
	public void doBackgroundTask() throws InterruptedException {
		logger.info("doing background task");
		if(random.nextInt(10) % 5 == 0) {
			throw new RuntimeException("random exception");
		}
		Thread.sleep(random.nextInt(1000));
	}
}
