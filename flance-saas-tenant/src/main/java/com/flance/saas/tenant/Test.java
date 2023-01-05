package com.flance.saas.tenant;

import com.flance.saas.db.init.InitTable;

public class Test {

    public static void main(String[] args) {
        InitTable.initSysTable(null, true, "com.flance.saas");
    }

}
