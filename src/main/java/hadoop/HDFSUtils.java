package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class HDFSUtils {

    private FileSystem fs = null;
    private String uri = "hdfs://192.168.233.3:9000/test.sql";
    @Before
    public  void  init() throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        //这个地方.方法的调用需要在方法里面,不是在类里面完成的
        conf.set("dfs.support.append","true");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy","NEVER");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.enable","true");

        //这里的get方法可以指定用户，不然会报错没有写入权限，
        // 也可以在运行的虚拟机中设置参数指定用户：
        //-DHADOOP_USER_NAME=root
        fs = FileSystem.get(URI.create(uri), conf, "root");
    }

    /**
     * 写入数据（增改）
     */
    @Test
    public void fileCopyWiehProgress() throws Exception {
        String localSrc = "C:\\Users\\xjc\\Desktop\\new 1";
        //新建（上传）文件，附带打印进度
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
         /*创建目录的方法，不过create方法会自动创建目录,所以没必要新建目录
         fs.mkdirs(new Path("hdfs://192.168.233.3:9000/targetdir")); */
        FSDataOutputStream out = fs.create(new Path(uri), () -> System.out.println("."));
        IOUtils.copyBytes(in, out, 4096, true);

    }

    /**
     *追加内容（增改）
     */
    @Test
    public void fileAppend() throws Exception {

        InputStream in = new ByteArrayInputStream("--测试：这是追加的内容".getBytes());
        FSDataOutputStream out = fs.append(new Path(uri));
        IOUtils.copyBytes(in, out, 4096, true);
    }

    /**
     * 读取数据（查）
     */
    @Test
    public void catFile() throws IOException {

        //InputStream in = fs.open(new Path(uri));
        FSDataInputStream in = fs.open(new Path(uri));
        try {
            IOUtils.copyBytes(in, System.out, 4096, false);
            //seek方法指定查询位置偏移量
            //in.seek(0);   //回到文档最初位置
        } finally {
            IOUtils.closeStream(in);
        }
    }


    /**
     * 查看文件或目录的信息（位置，大小等）（查）
     */
    @Test
    public void showFileStatus() throws IOException {
        //书中给出的测试语法，断言新用法
        String tt = "aaa";
        assertThat(tt, is("aaa"));
        FileStatus stat = fs.getFileStatus(new Path(uri));
        System.out.println(stat);
    }

    /**
     * 列出文件（查）
     */
    @Test
    public void listFileStatus() throws IOException {
        FileStatus[] stat = fs.listStatus(new Path("hdfs://192.168.233.3:9000/"));
        for (FileStatus a : stat) {
            System.out.println(a);
        }
        //将列出的文件信息转换成路径信息
        Path[] listPath = FileUtil.stat2Paths(stat);
        for (Path a : listPath) {
            System.out.println(a);
        }
    }

    /**
     * 删除文件（删）
     */
    @Test
    public void rmFile() throws IOException {
        fs.delete(new Path(uri), true);
    }
}
