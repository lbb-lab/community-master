//package life.majiang.community.test.rsa;
//
//import org.apache.commons.codec.binary.Base64;
//import org.bouncycastle.asn1.ASN1InputStream;
//import org.bouncycastle.asn1.ASN1Primitive;
//import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
//
//import java.security.KeyFactory;
//import java.security.spec.RSAPrivateKeySpec;
//
///**
// * @author: liu bin bin
// * @Date: 2021/3/8 11:02
// * @Description:
// */
//public class RsaTest {
//
//
//    public static void main(String[] args) throws Exception{
//
//        String publickey = "-----BEGIN RSA PRIVATE KEY-----\n" +
//                "MIIEpAIBAAKCAQEApuLMI/D4mQFJxLXoKNye7rCmS3/hVyFFielDLUO3zB9aVrd+\n" +
//                "gVUDHXaidB0sXhIlW+zxIhlfZL/8/fgw8GSXUiqiby4w5YP5pqrKWUXrDIHTGVnc\n" +
//                "ugQElwZoqQqfeLI4rX96pSfjUp0lFQwdWmbQY4s2noFBPLYkX8u/rMWljK+8uQBz\n" +
//                "DHhlEZp7rFeaqKD7afRRfaVVi8E9+LPxrbuRhuGBfNOZ9QONrIEKSRO6044YqLPj\n" +
//                "s+waQpk7Yy8E7p5NuulisSJho446g5c7qbynzovd8t37/NWNaotQCz9gWc52gWrv\n" +
//                "ZeFCyvRlSFcQWg84/Rw8pAUishgRwD8kkj6trQIDAQABAoIBADgv6c08B2b0URLi\n" +
//                "r+x6AySX4xVOU98S+6eoGo712r7l23OQyg3fQnI8DAwN+m8dquhdSP5TDpQMM7CK\n" +
//                "TGfTZkMqyY+nPJ05pucfX4tZB/8pAprpb3BNhAIZDUnEotZvIrf/UJqkaFvbPq3s\n" +
//                "a0GRlh29tED/gahgJrT+E2WnEcrgoN6fsbokbvgR9d+xPxs9LGjTK+Wcu8gvJwYC\n" +
//                "iYmTsr1WXJ2oZITVGNHAVsIW185XeCUhp+E3074gEXgyFMZV7zFkSzZY5fLaAJKx\n" +
//                "S7swy+HGJWmhPiPex9hrKQDpmi25r6wk+IOTIv5O/hfGbFVOHwk9PzzdDyQF5r+U\n" +
//                "sryEFcECgYEA1vS//Mk8E/j3xdZHB5/YiLBFBAl749Zyl6uYMVDkTiIPq3KbOhnT\n" +
//                "kMy6DYilerLmD2bPuQfl5UKdxSXY7d4o1x/K0lIMJ54wJO+glpyQzxgDvaeX5Ti7\n" +
//                "fc+l2AEsguuRckPfcIDQD1kTdz1kx5EufCG72s3KpC0nPhvPPjtMmxECgYEAxsBV\n" +
//                "2iWaHkvk+G+p0ShoM4pXbnzGYYkvbQ38jwSF5+BMqvv+9xwm6Es19JE2/bEfUrn2\n" +
//                "rAB6rknmvsjMboQWrgOCYZuLQ9nQWSnmEnEd+zATKdJrOtjhzkV7VACfYHL8M+Rj\n" +
//                "E/ax3yOQkbRXrHYadEIt5V5OT36+pn428/sv0N0CgYEAyhG6iUADIiNhiLEuhJRX\n" +
//                "UeeieXEQ7dWiU8ITiAirFmgeouVR1wSARhausBpMtVOHweK8UwyBqUs+VR6HFtVJ\n" +
//                "PzG70Isgsf2a9J3vMKZt2sCQg7EQsYLrfhG9rZks5sXcLLZxTalJ7aOnEOi93H02\n" +
//                "Xaq4la/vVva7dT9fSvkjwoECgYAcmRRBXWO9tEHwFXEj7WKA+Aj4lu+cnTa7xGVw\n" +
//                "1r/Fy5p8h+H6AI0Ao8HRvQtimZfeOW/q8+6zIWMYVKBFzq/MviiGjvENBExcaBD4\n" +
//                "2lnsCNDZFbPkNczX91t1jNlh8Wmktdojxz101BM7Q+3RnPTVgahE9rrh6WTZZBPY\n" +
//                "K26H6QKBgQCYHUyzqazpJ/bO9dUDORtfc4uFSsgzewk4VilGGEnA/70s+AOK4y/l\n" +
//                "KBz+KDEqD/zL8KrsRIdVvJie391pJC979GbaPGQ17e0cOkPsTFIxCvkHrlYqwJ2b\n" +
//                "QQv/5ouAzX84/2KvV9/F/gPBcqjjdChsHFvqKV6osNQpls8ZB23/aA==\n" +
//                "-----END RSA PRIVATE KEY-----\n";
//
//        byte[] keybyte = Base64.decodeBase64(publickey);
//
//        ASN1InputStream in=new ASN1InputStream(keybyte);
//
//        ASN1Primitive obj=in.readObject();
//
//        RSAPrivateKeyStructure pStruct=RSAPrivateKeyStructure.getInstance(obj);
//
//        RSAPrivateKeySpec spec=new RSAPrivateKeySpec(pStruct.getModulus(), pStruct.getPrivateExponent());
//
//        KeyFactory keyFactory= KeyFactory.getInstance("RSA");
//
//        priKey=keyFactory.generatePrivate(spec);
//
//
//
//
//    }
//
//
//
//}
