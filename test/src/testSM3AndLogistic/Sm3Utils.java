package testSM3AndLogistic;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Arrays;

/**
 * @Author:
 * @Date: 2020/4/21 5:04
 * @Description: *
 * sm3加密算法工具类
 * @explain 加密与加密结果验证（不可逆算法）
 */

public class Sm3Utils {
    private static final String ENCODING = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    //1、SM3加密算法 不提供密钥
    public static String encrypt(String paramStr) {
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hash(srcData);
            // 将返回的hash值转换成16进制字符串
            resultHexString = ByteUtils.toHexString(resultHash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultHexString;
    }

    /**
     * 返回长度=32的byte数组
     *
     * @param srcData
     * @return
     * @explain 生成对应的hash值
     */
    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }



    //3、加密数据校验
    /* 判断源数据与加密数据是否一致
     * @explain 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
     * @param srcStr
     *            原字符串
     * @param sm3HexString
     *            16进制字符串
     * @return 校验结果
     */
    public static boolean verify(String srcStr, String sm3HexString) {
        boolean flag = false;
        try {
            byte[] srcData = srcStr.getBytes(ENCODING);
            byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
            byte[] newHash = hash(srcData);
            if (Arrays.equals(newHash, sm3Hash))
                flag = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //4.测试
    public static void main(String[] args) {
        // 测试二：json
        String json = "{\"name\":\"Marydon\",\"website\":\"http://www.cnblogs.com/Marydon20170307\"}";
        String hex = Sm3Utils.encrypt(json);
        System.out.println(hex);// 0b0880f6f2ccd817809a432420e42b66d3772dc18d80789049d0f9654efeae5c
        // 验证加密后的16进制字符串与加密前的字符串是否相同
        boolean flag = Sm3Utils.verify(json, hex);
        System.out.println(flag);// true
        System.out.println("----------------------------------");
        System.out.println(hex = Sm3Utils.encrypt("abc"));
        System.out.println(Sm3Utils.verify(("abc"),hex));

    }
}

