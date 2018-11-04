package csk.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZkTest implements Watcher {

    ZooKeeper zookeeper = null;

    @Before
    public void init() throws IOException {
        zookeeper = new ZooKeeper("192.168.142.161:2181", 5000, this);
    }

    @Test
    public void addNode() throws KeeperException, InterruptedException, IOException {
        String val = "接点数据";
        //创建节点
        String zkTest = zookeeper.create("/zkTest", val.getBytes("gb2312"),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zkTest);
        //创建子节点
        String children = zookeeper.create("/zkTest/children", "我爱你Zookeeper".getBytes("gb2312"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(children);
        //获取节点数据
        byte[] data = zookeeper.getData("/zkTest/children", this::process, new Stat());
        System.out.println(new String(data, "gb2312"));
        //设置节点数据
        zookeeper.setData("/zkTest/children", "Zookeeper我也爱你哈".getBytes("gb2312"), 0);
        List<String> children1 = zookeeper.getChildren("/zkTest", true);
        System.out.println(children1);
    }

    @Test
    public void deleteNode() throws KeeperException, InterruptedException {
        Stat exists = zookeeper.exists("/zkTest", this::process);
        if (exists != null) {
            deleteNodeAndSubNode(zookeeper, "/zkTest");
            System.out.println("删除成功！");
        } else {
            System.out.println("节点不存在");
        }
    }

    /**
     * 递归删除当前节点及子节点
     * @param zk
     * @param nodeStr
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void deleteNodeAndSubNode(ZooKeeper zk, String nodeStr) throws KeeperException, InterruptedException {
        String nodePath = nodeStr;
        //打印当前节点路径
        System.out.println("Node Path -----> 【" + nodePath + " 】");
        if (zk.getChildren(nodePath, false).size() == 0) {
            //删除节点
            System.out.println("Deleting Node Path -----> 【" + nodePath + " 】");
            zk.delete(nodePath, -1);
        } else {
            //递归查找非空子节点
            List<String> list = zk.getChildren(nodeStr, true);
            for (String str : list) {
                deleteNodeAndSubNode(zk, nodePath + "/" + str);
            }
            zk.delete(nodePath, -1);
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("已经触发了" + watchedEvent.getType() + "事件！");
    }

    @After
    public void destroy() throws InterruptedException {
        zookeeper.close();
    }
}