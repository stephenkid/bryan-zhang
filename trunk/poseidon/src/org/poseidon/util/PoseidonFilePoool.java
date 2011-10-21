package org.poseidon.util;

import org.nutz.filepool.NutFilePool;

public class PoseidonFilePoool extends NutFilePool {

    public PoseidonFilePoool(String homePath) {
        super(homePath);
    }
    
    public PoseidonFilePoool(String homePath, long size) {
        super(homePath, size);
    }
    
    
}
