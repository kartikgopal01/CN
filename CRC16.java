public class CRC16 { 
    private static final int POLYNOMIAL = 0xA001; 

    public static int calculateCRC(byte[] data) { 
        int crc = 0xFFFF; 
        for (byte b : data) { 
            crc ^= b & 0xFF; 
            for (int i = 0; i < 8; i++) { 
                if ((crc & 0x0001) != 0) { 
                    crc = (crc >> 1) ^ POLYNOMIAL; 
                } else { 
                    crc >>= 1; 
                } 
            } 
        } 
        return crc; 
    } 

    public static boolean checkCRC(byte[] data, int receivedCRC) { 
        int calculatedCRC = calculateCRC(data); 
        return calculatedCRC == receivedCRC; 
    } 

    public static void main(String[] args) { 
        String input = "Hello, CRC!"; 
        byte[] data = input.getBytes(); 

        int crc = calculateCRC(data); 
        System.out.printf("CRC-CCITT (16-bit) for '%s': %04X%n", input, crc); 

        byte[] receivedData = data; 
        int receivedCRC = crc; 

        boolean isValid = checkCRC(receivedData, receivedCRC); 
        System.out.println("Data valid: " + isValid); 
    } 
}