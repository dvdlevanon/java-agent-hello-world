package test;

import java.lang.instrument.Instrumentation;

public class SimpleJavaAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
		startBackgroundThread("premain", agentArgs);
	}

	public static void agentmain(String agentArgs, Instrumentation inst) {
		startBackgroundThread("agentmain", agentArgs);
	}
	
	private static void startBackgroundThread(String source, String agentArgs) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("Agent " + source + ": " + agentArgs);
					
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}
			
		}, "test-agent").start(); 
	}
}
