package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;

public class StreamCompressor {
    public static void main(String[] args) throws ClassNotFoundException {
        String codeClassname =null;
        //forname方法返回一个对象，该对象由参数决定，如forName(java.lang.string)
        //代表这个codeClass是一个字符串对象
        Class<?> codeClass = Class.forName(codeClassname);
        Configuration conf= new Configuration();
        CompressionCodec codec = (CompressionCodec)
                ReflectionUtils.newInstance(codeClass,conf);


    }
}
