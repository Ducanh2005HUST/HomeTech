package hometech.util;

import com.password4j.Password;
import com.password4j.Hash;

public class HashPasswordUtil {
    // Băm mật khẩu bằng Argon2id
    public static String hashPassword(String password) {
        Hash hash = Password.hash(password).addRandomSalt().withArgon2();
        return hash.getResult();
    }

    // Xác thực mật khẩu với hash đã lưu
    public static boolean verifyPassword(String password, String hash) {
        return Password.check(password, hash).withArgon2();
    }
}
