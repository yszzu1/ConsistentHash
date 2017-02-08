
import java.util.ArrayList;
import java.util.Collection;

public class HashTest
{
    public static void main(String[] args)
    {
        Collection<PhysicalNode> ls = new ArrayList<PhysicalNode>();
        ls.add(new PhysicalNode("china", "192.168.121.1", 5080));
        ls.add(new PhysicalNode("china", "192.168.121.2", 5080));
        ls.add(new PhysicalNode("china", "192.168.121.3", 5080));
        ls.add(new PhysicalNode("china", "192.168.121.4", 5080));
        ls.add(new PhysicalNode("china", "192.168.121.5", 5080));
        
        ConsistentHashRouter route = new ConsistentHashRouter(ls, 1);
        route.printRingKeys();
        
        PhysicalNode node = route.getNode("ASDFaaaaa");
        System.out.println(node.getIp());
        node = route.getNode("jalskdfjasdf");
        System.out.println(node.getIp());
        node = route.getNode("ASDFaaaaa");
        System.out.println(node.getIp());
        
        //一个物理节点挂掉后，需要从hash环中删除之
        PhysicalNode pNode = new PhysicalNode("china", "192.168.121.5", 5080);
        route.removeNode(pNode);
        
        //挂掉后的物理节点中存放的key，迁移到其他节点。而且不影响已经存在的节点
        node = route.getNode("ASDFaaaaa");
        System.out.println(node.getIp());
        node = route.getNode("jalskdfjasdf");
        System.out.println(node.getIp());
    }
}
