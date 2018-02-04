package pl.rp.ws.controller;

public class TestController {
	
	private static long traceCounterLong = 0;
	private static int debugCounterInt = 0;
	private static int levelCounterInt = 0;
	private static final boolean TRACE_FLAG = true;
	
	public enum InsertType {
		IN, OUT, INOUT, DEBUG
	}

	public static long getTraceCounterLong() {
		return traceCounterLong;
	}

	public static void setTraceCounterLong(long traceCounterLong) {
		TestController.traceCounterLong = traceCounterLong;
	}

	public static int getDebugCounterLong() {
		return debugCounterInt;
	}

	public static void setDebugCounterInt(int debugCounterLong) {
		TestController.debugCounterInt = debugCounterLong;
	}

	public static boolean isTraceFlag() {
		return TRACE_FLAG;
	}

	public static void traceCounter(String messageString) {
		if(TRACE_FLAG)
			System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + messageString);
	}

	public static void traceCounter(int typeInt, String messageString) {
		if(TRACE_FLAG) {
			if(typeInt == 1 || typeInt == 3) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (++levelCounterInt)) + "]: " + " IN: " + messageString);
			}
			if(typeInt == 2 || typeInt == 3) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (levelCounterInt--)) + "]: " + "OUT: " + messageString);
			}
			if(typeInt == 4) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (++debugCounterInt)) + "]: " + "DEB: " + messageString);
			}
		}
	}
	
	public static void traceCounter(InsertType typeEnum, String messageString) {
		if(TRACE_FLAG) {
			if(typeEnum == InsertType.IN || typeEnum == InsertType.INOUT) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (++levelCounterInt)) + "]: " + " IN: " + messageString);
			}
			if(typeEnum == InsertType.OUT || typeEnum == InsertType.INOUT) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (levelCounterInt--)) + "]: " + "OUT: " + messageString);
			}
			if(typeEnum == InsertType.DEBUG) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (++debugCounterInt)) + "]: " + "DEB: " + messageString);
			}
		}
	}
	
	public static void traceCounter(InsertType typeEnum, String packageNameString, String classNameString, String methodNameString, String parametersString, String messageString) {
		if(TRACE_FLAG) {
			String tmpString = "[" + packageNameString + "][" + classNameString + "][" + methodNameString + "("+ parametersString + ")] " + messageString ;
			if(typeEnum == InsertType.IN || typeEnum == InsertType.INOUT) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (++levelCounterInt)) + "]: " + " IN: " + tmpString);
			}
			if(typeEnum == InsertType.OUT || typeEnum == InsertType.INOUT) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (levelCounterInt--)) + "]: " + "OUT: " + tmpString);
			}
			if(typeEnum == InsertType.DEBUG) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "][" + String.format("%02d", (++debugCounterInt)) + "]: " + "DEB: " + tmpString);
			}
		}
	}
}
