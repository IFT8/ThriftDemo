package cn.demo.thrift.sync;

import cn.demo.thrift.SayHiServiceBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by IFT8
 * on 2016/5/25.
 */
public class SyncClient {
    public static void main(String[] args) {
        // 设置调用的服务地址为本地，端口为 3813
        TTransport transport = new TSocket("localhost", 3813);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        // 设置传输协议为 TBinaryProtocol
        TProtocol protocol = new TBinaryProtocol(transport);
        SayHiServiceBase.Client client = new SayHiServiceBase.Client(protocol);
        // 调用服务方法
        try {
            String s = client.sayHello("123");
            System.out.println(s);

        } catch (TException e) {
            e.printStackTrace();
        }
        transport.close();
    }
}
