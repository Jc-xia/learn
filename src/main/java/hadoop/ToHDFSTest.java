package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

public class ToHDFSTest {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(args[1]), conf);

        InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
        FSDataOutputStream out = fs.create(new Path(args[1]), () -> System.out.println("..."));

        IOUtils.copyBytes(in,out,4096,true);
    }


}
