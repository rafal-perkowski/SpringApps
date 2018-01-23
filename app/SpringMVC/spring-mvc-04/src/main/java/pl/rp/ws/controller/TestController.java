package pl.rp.ws.controller;

public class TestController {
	
	private static long traceCounterLong = 0;
	private static long debugCounterLong = 0;
	private static final boolean traceFlagBoolean = true;
	
	public enum InsertType {
		IN, OUT, INOUT, DEBUG
	}

	public static long getTraceCounterLong() {
		return traceCounterLong;
	}

	public static void setTraceCounterLong(long traceCounterLong) {
		TestController.traceCounterLong = traceCounterLong;
	}

	public static long getDebugCounterLong() {
		return debugCounterLong;
	}

	public static void setDebugCounterLong(long debugCounterLong) {
		TestController.debugCounterLong = debugCounterLong;
	}

	public static boolean isTraceFlagBoolean() {
		return traceFlagBoolean;
	}

	public static void traceCounter(String messageString) {
		if(traceFlagBoolean)
			System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + messageString);
	}

	public static void traceCounter(int typeInt, String messageString) {
		if(traceFlagBoolean) {
			if(typeInt == 1 || typeInt == 3) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + " IN: " + messageString);
			}
			if(typeInt == 2 || typeInt == 3) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + "OUT: " + messageString);
			}
			if(typeInt == 4) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]:" + "[" + String.format("%02d", (++debugCounterLong)) + "]: " + messageString);
			}
		}
	}
	
	public static void traceCounter(InsertType typeEnum, String messageString) {
		if(traceFlagBoolean) {
			if(typeEnum == InsertType.IN || typeEnum == InsertType.INOUT) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + " IN: " + messageString);
			}
			if(typeEnum == InsertType.OUT || typeEnum == InsertType.INOUT) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]: " + "OUT: " + messageString);
			}
			if(typeEnum == InsertType.DEBUG) {
				System.out.println("[" + String.format("%04d", (++traceCounterLong)) + "]:" + "[" + String.format("%02d", (++debugCounterLong)) + "]: " + messageString);
			}
		}
	}
}
