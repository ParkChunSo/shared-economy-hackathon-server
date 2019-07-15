package com.hackathon.sharedeconomy;

import com.hackathon.sharedeconomy.model.enums.ImageType;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import org.junit.Test;

public class EnumTest {

    @Test
    public void Test(){
        System.out.println(RoleType.ADMIN);
        System.out.println(RoleType.ADMIN.getRoleExplain());
    }
}
