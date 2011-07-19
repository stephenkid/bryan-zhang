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
      buffer.clear();

      int r = fcin.read( buffer );

      if (r==-1) {
        break;
      }

      buffer.flip();//像指针一样的东西，把缓冲区的读取位置归零，这样就可以把缓冲区里的所有都写进去了。

      fcout.write( buffer );
    }
  }
}
