package cn.edu.gxu.gxucpcsystem.domain;


import org.junit.jupiter.api.Test;

public class AdminTest {
    @Test
    public void TestAdmin() {
        Admin admin = new Admin();
        System.out.println(admin.checkIntegrityCreate());
    }

}
