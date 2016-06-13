package cn.demo.thrift.sync;

import cn.demo.thrift.SayHiServceImpl;
import cn.demo.thrift.SayHiServiceBase;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by IFT8
 * on 2016/5/25.
 */
public class SyncServer {
    public static void main(String[] args) {
        // 设置服务端口为 3813
        TServerSocket serverTransport = null;
        try {
            serverTransport = new TServerSocket(3813);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        // 关联处理器与 Hello 服务的实现
        TProcessor processor = new SayHiServiceBase.Processor<>(new SayHiServceImpl());
        TThreadPoolServer.Args argss = new TThreadPoolServer.Args(
                serverTransport);
//        // 设置协议工厂为 TBinaryProtocol.Factory
//        TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
//        // 默认情况就是TBinaryProtocol.Factory
//        argss.inputProtocolFactory(proFactory);
//        argss.outputProtocolFactory(proFactory);
        argss = argss.processor(processor);
        TServer server = new TThreadPoolServer(argss);
        System.out.println("Start server on port 3813...");
        server.serve();
    }
}
