package test;

import java.io.File;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class AgentInjector {
	public static void main(String[] args) throws Exception {
		String filter = "all";
		
		if (args.length < 1) {
			System.out.println("Usage: java ... test.AgentInjector path/to.jar");
			return;
		}
		
		File agentJar = new File(args[0]);
		
		if (!agentJar.canRead()) {
			System.out.println("Agent jar is missing: " + agentJar);
			return;
		}
		
		if (args.length > 1) {
			filter = args[1];
		}
		
		System.out.println("Attaching to JVM: (" + filter + ")");
		
		for (VirtualMachineDescriptor vmd : VirtualMachine.list()) {
			boolean shouldAttach = ("all".equalsIgnoreCase(filter) || filter.equalsIgnoreCase(vmd.displayName()));
			
			if (!shouldAttach) {
				System.out.println("Skipping JVM " + vmd.displayName());
				continue;
			}
						
			System.out.println("Attaching to " + vmd.displayName());
			
			try {
				VirtualMachine vm = VirtualMachine.attach(vmd.id());
				
				vm.loadAgent(agentJar.getCanonicalPath(), "param1 param2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
