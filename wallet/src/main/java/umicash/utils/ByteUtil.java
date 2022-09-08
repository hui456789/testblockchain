package umicash.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;

import static org.bitcoinj.core.Utils.reverseBytes;

public class ByteUtil {
    public static final int UINT_32_LENGTH = 4;
    public static final int UINT_64_LENGTH = 8;
    public static byte[] readBytes(byte[] buf, int offset, int length) {
        byte[] b = new byte[length];
        System.arraycopy(buf, offset, b, 0, length);
        return b;
    }

    public static BigInteger readUint64(byte[] buf, int offset) {
        return new BigInteger(reverseBytes(readBytes(buf, offset, UINT_64_LENGTH)));
    }

    public static void uint64ToByteStreamLE(BigInteger val, OutputStream stream) throws IOException {
        byte[] bytes = val.toByteArray();
        if (bytes.length > 8) {
            if (bytes[0] == 0) {
                bytes = readBytes(bytes, 1, bytes.length - 1);
            } else {
                throw new RuntimeException("Input too large to encode into a uint64");
            }
        }
        bytes = reverseBytes(bytes);
        stream.write(bytes);
        if (bytes.length < 8) {
            for (int i = 0; i < 8 - bytes.length; i++)
                stream.write(0);
        }
    }

    private static byte[] trimLeadingBytes(byte[] bytes, byte b) {
        int offset = 0;
        for (; offset < bytes.length - 1; offset++) {
            if (bytes[offset] != b) {
                break;
            }
        }
        return Arrays.copyOfRange(bytes, offset, bytes.length);
    }

    public static byte[] trimLeadingZeroes(byte[] bytes) {
        return trimLeadingBytes(bytes, (byte) 0);
    }

    public static byte[] concat(byte[] b1, byte[] b2) {
        byte[] result = Arrays.copyOf(b1, b1.length + b2.length);
        System.arraycopy(b2, 0, result, b1.length, b2.length);
        return result;
    }
}