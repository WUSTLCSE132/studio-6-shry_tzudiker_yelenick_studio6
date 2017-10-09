package studio6;

import jssc.*;

public class SerialComm {

	private static SerialPort port;

	private static boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public static boolean writeByte(byte b) {
		if (debug) {
			System.out.println(String.format("<0x%02x>", b));
		}
		try {
			return port.writeByte(b);
		} catch (Exception e) {
			return false;
		}
	}
	
	// TODO: Add available() method
	public static boolean available() {
		try {
			return port.getInputBufferBytesCount() > 0;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	// TODO: Add readByte() method
	public static byte readByte() {
		try {
			return port.readBytes(1)[0];
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (byte)-1;
		}
	}
	
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException {
		SerialComm sc = new SerialComm("COM6");
		while (true) {
			if (available()) {
				byte bite = readByte();
				System.out.println(bite);
				if (debug) {
					System.out.println(
						String.format("0x%02x", bite)
					);
				}
			}
		}
	}
}
