package cn.demo.thrift.async.framedTransport.nio;

import cn.demo.thrift.SayHiServceImpl;
import cn.demo.thrift.SayHiServiceBase;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by IFT8
 * on 2016/5/25.
 */
public class AsyncServer {
    public static void main(String[] args) {
        // 设置服务端口为 3214
        TNonblockingServerTransport serverTransport = null;
        try {
            serverTransport = new TNonblockingServerSocket(3214);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        // 关联处理器与 Hello 服务的实现
        SayHiServiceBase.Processor processor = new SayHiServiceBase.Processor<>(
                new SayHiServceImpl());
        // TServer server = new TNonblockingServer(processor,
        // serverTransport);
        TNonblockingServer.Args argss = new TNonblockingServer.Args(
                serverTransport);
        argss.processor(processor);
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        argss.transportFactory(new TFramedTransport.Factory());
        argss.protocolFactory(new TCompactProtocol.Factory());

        TServer server = new TNonblockingServer(argss);
        System.out.println("Start server on port 3214 ...");
        server.serve();
    }
}
