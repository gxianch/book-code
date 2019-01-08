package cn.itcast.bigdata.zk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class AppClient {
	private String groupNode = "sgroup";
	private ZooKeeper zk;
	private Stat stat = new Stat();
	private volatile List<String> serverList;
	private static final String connectString = "10.10.1.110:2181,10.10.1.111:2181,10.10.1.112:2181";
	private static final int sessionTimeout = 50000;
	public void connectZookeeper() throws IOException {
		zk = 	new ZooKeeper(connectString, sessionTimeout, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				if(event.getType() == EventType.NodeChildrenChanged 
						&& ("/"+groupNode).equals(event.getPath())) {
					updateServerList();
				}
			}

		});
		updateServerList();
	}
	private void updateServerList() {
		List<String> newServerList = new ArrayList<String>();
		try {
			List<String> subList = zk.getChildren("/"+groupNode, true);
			for(String subNode:subList) {
				byte[] data = zk.getData("/"+groupNode+"/"+subNode, false, stat);
				newServerList.add(new String(data,"utf-8"));
			}
			serverList = newServerList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * client的工作逻辑写在这个方法中
	 * 此处不做任何处理, 只让client sleep
	 */
	public void handle() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}
	public static void main(String[] args) throws Exception {
		AppClient ac = new AppClient();
		ac.connectZookeeper();
		ac.handle();
	}
}
