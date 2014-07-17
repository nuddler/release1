package amg.net.filewalker;

import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

	
	private final AndOrEnum type;

	public AppConfiguration(AndOrEnum type) {
		this.type = type;
	}
	
	

	public AppConfiguration() {
		type = AndOrEnum.AND;
	}



	public AndOrEnum getType() {
		return type;
	}
	
	
}
