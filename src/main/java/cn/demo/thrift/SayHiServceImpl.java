package cn.demo.thrift;

import org.apache.thrift.TException;

/**
 * Created by IFT8
 * on 2016/5/25.
 */
public class SayHiServceImpl implements SayHiServiceBase.Iface {
    @Override
    public String sayHello(String username) throws TException {
        System.out.println("I'm " + username);
        return username + " Hi~Iface~ " + Thread.currentThread().getId() + "|" + Thread.currentThread().getName();
    }
}
