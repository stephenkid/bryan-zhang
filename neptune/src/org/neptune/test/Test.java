package org.neptune.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Bean> list = new ArrayList<Bean>();
        for (int i = 0; i < 10; i++) list.add(new Bean("str" + i));
        
        //for (int j = 0; j < 2; j++){
            /*for (Iterator<Bean> it = list.iterator(); it.hasNext();) {
                Bean tmp = it.next();
                if (tmp.getS().equals("str3")){
                    System.out.println("ready to remove");
                    it.remove();
                    break;
                }
            }*/
            
            for (Bean tmp : list){
                if (tmp.getS().equals("str3")){
                    System.out.println("ready to remove");
                    list.remove(tmp);
                    //break;
                }
            }
        //}
        
        System.out.println(list.size());

    }

}
