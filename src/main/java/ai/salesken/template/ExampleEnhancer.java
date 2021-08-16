package ai.salesken.template;

import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.LoggingEnhancer;

// Add / update additional fields to the log entry
public class ExampleEnhancer implements LoggingEnhancer {

	public void enhanceLogEntry(LogEntry.Builder logEntry) {
		// add additional labels
		logEntry.addLabel("k,", "test-value-1");
	}
}