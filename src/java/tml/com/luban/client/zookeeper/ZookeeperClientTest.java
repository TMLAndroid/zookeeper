package com.luban.client.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZookeeperClientTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper client = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("连接的时候"+event);
            }
        });
        Stat stat = new Stat();
        client.getData("/testW", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.EventType.NodeDataChanged.equals(event.getType())){
                    System.out.println("数据发送发生了改变");
                }
            }
        },stat);
        System.in.read();
    }
}
