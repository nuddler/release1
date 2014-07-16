package amg.net.filewalker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class FileWalkerAspect {
	private static final Logger logger =  LogManager.getLogger(FileWalkerAspect.class);

	  @Pointcut("execution(* amg.net.filewalker..*.*(..))") //1
	  public void handleDaoMethod() {}

	  @Before("amg.net.filewalker.FileWalkerAspect.handleDaoMethod()") //2
	  public void before(JoinPoint _jp) {
		  logger.error("before");
	  }

	  @Around("amg.net.filewalker.FileWalkerAspect.handleDaoMethod()") //3
	  public Object logExecutionTime(ProceedingJoinPoint _pjp) throws Throwable {
	    String className  = _pjp.getTarget().getClass().getSimpleName();
	    String methodName = _pjp.getSignature().getName()+"()";
	    logger.error("Method "+className+"."+methodName);

	    long time = System.currentTimeMillis();

	    try {
	      return _pjp.proceed();
	    } finally {
	      time = System.currentTimeMillis() - time;
	      logger.error("Method "+className+"."+methodName+" successfully executed in "+time+" ms");
	    }
	  }
}
