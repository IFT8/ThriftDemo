package cn.demo.thrift.async.framedTransport;

import cn.demo.thrift.SayHiServiceBase;
import cn.demo.thrift.async.Callback;
import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by IFT8
 * on 2016/5/25.
 */
public class AsyncClient {
    public static void main(String[] args) throws TException, InterruptedException, IOException {
        TAsyncClientManager clientManager = null;
        try {
            clientManager = new TAsyncClientManager();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        TNonblockingTransport transport;
        try {
            transport = new TNonblockingSocket(
                    "localhost", 3214);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TProtocolFactory protocol = new TCompactProtocol.Factory();
        SayHiServiceBase.AsyncClient asyncClient = new SayHiServiceBase.AsyncClient(protocol,
                clientManager, transport);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Callback callback = new Callback(countDownLatch);

            System.out.println("call method sayHello start ...");
            asyncClient.sayHello("123", callback);
            System.out.println("call method sayHello .... end");
            boolean wait = countDownLatch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);

        } catch (TException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
