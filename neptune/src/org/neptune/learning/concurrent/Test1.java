package org.neptune.learning.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test1 {

    /**
     * @param args
     */
    private static List<String> list = new CopyOnWriteArrayList<String>();
    
    
    public static void main(String[] args) throws Throwable {
        String[] array = new String[]{"aaa","bbb","ccc","ddd","eee"};
        list.addAll(Arrays.asList(array));
        
        for(int i = 0; i < 4;i++){
            Test1 test = new Test1();
            test.new myTread().start();
            
            System.out.println(list);
        }
        System.out.println(list);
    }

    class myTread extends Thread{
        @Override
        public void run() {
            System.out.println("start remove");
            list.remove(0);
            System.out.println("end remove");
        }
    }
    
}
 