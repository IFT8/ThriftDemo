package cn.demo.thrift.async;

import cn.demo.thrift.SayHiServiceBase;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IFT8
 * on 2016/5/25.
 */
public class Callback implements AsyncMethodCallback<SayHiServiceBase.AsyncClient.sayHello_call>  {
    private CountDownLatch latch;

    public Callback(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onComplete(SayHiServiceBase.AsyncClient.sayHello_call sayHello_call) {
        try {
            String result=sayHello_call.getResult();
            System.out.println(result);
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }

    @Override
    public void onError(Exception e) {
        System.out.println(e.getMessage());
        latch.countDown();
    }

}
