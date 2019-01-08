package cn.itcast.bigdata.zk;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

public class SimpleZkClient {
	private static final String connectString = "10.10.1.110:2181,10.10.1.111:2181,10.10.1.112:2181";
	private static final int sessionTimeout = 50000;
	ZooKeeper zkClient = null;
	@Before
	public void init() throws Exception {
		zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event.getType()+"---"+event.getPath());
				try {
					zkClient.getChildren("/", true);
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	@Test
	public void testCreate() throws KeeperException, InterruptedException {
		// 参数1：要创建的节点的路径 参数2：节点大数据 参数3：节点的权限 参数4：节点的类型
		String nodeCreated = zkClient.create("/eclipse", "hellozk".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//上传的数据可以是任何类型，但都要转成byte[]
	}
	@Test
	public void testCreate1() throws KeeperException, InterruptedException {
		// 参数1：要创建的节点的路径 参数2：节点大数据 参数3：节点的权限 参数4：节点的类型
		String nodeCreated = zkClient.create("/sgroup/sub", "10.10.1.112:2181".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		//上传的数据可以是任何类型，但都要转成byte[]
	}
	@Test	
	public void testExist() throws Exception{
		Stat stat = zkClient.exists("/eclipse", false);
		System.out.println(stat == null? "not exist":"exist");
	}
	// 获取子节点
		@Test
		public void getChildren() throws Exception {
		List<String> children =	zkClient.getChildren("/", true);
		children.stream().forEach(System.out::println);
		}
		//获取znode的数据
		@Test
		public void getData() throws Exception {
			byte[] data = zkClient.getData("/eclipse", false, null);
			System.out.println(new String(data));
		}
		//删除znode
		@Test
		public void deleteZnode() throws Exception {
			//参数2：指定要删除的版本，-1表示删除所有版本
			zkClient.delete("/app1", -1);
		}
		//删除znode
		@Test
		public void setData() throws Exception {
			zkClient.create("/app1", null, Ids.OPEN_ACL_UNSAFE	, CreateMode.EPHEMERAL);
			zkClient.setData("/app1", "imissyou angelababy".getBytes(), -1);
			
			byte[] data = zkClient.getData("/app1", false, null);
			System.out.println(new String(data));
			
		}
		
}
