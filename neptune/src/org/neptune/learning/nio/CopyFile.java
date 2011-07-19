package org.neptune.learning.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class CopyFile
{
  static public void main( String args[] ) throws Exception {
   

    String infile = "c:/aaa.txt";
    String outfile = "c:/ccc.txt";

    FileInputStream fin = new FileInputStream( infile );
    FileOutputStream fout = new FileOutputStream( outfile );

    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

    while (true) {
    	
      /**
       * clear做了2件事情
       * 它将 limit 设置为与 capacity 相同
       * 它设置 position 为 0
       */
      buffer.clear();

      int r = fcin.read( buffer );

      if (r==-1) {
        break;
      }

      /**
       * flip做了2件事情
       * 它将 limit 设置为当前 position，它将 position 设置为 0
       * 这个就像指针一样的，确保能读到以前所有的数据，并且一个也不多读
       */
      buffer.flip();

      fcout.write( buffer );
    }
  }
}
