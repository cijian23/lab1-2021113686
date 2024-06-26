
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
// 在B2分支上对2个文件做修改并提交
/* 在这里添加注释，用于测试R3——查看上次提交之后都有哪些文件修改、具体修改内容是什么 */
/* 在这里添加注释，用于测试R5——再次对部分文件进行修改，重新提交 */


/* 用于测试分支管理——在C4上，对2个文件进行修改并提交 */




// 在B1分支上对同样的2个文件做不同修改并提交
public class GraphDemo {

    protected Map<String, Integer> nameToIndex; // 节点名称到索引的映射
    protected List<String> indexToName;    // 索引到节点名称的映射
    protected List<List<Integer>> adj;     // 邻接表

    public GraphDemo(int V) {
        nameToIndex = new HashMap<>();
        indexToName = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addNode(String name) {
        if (!nameToIndex.containsKey(name)) {
            int index = nameToIndex.size();
            nameToIndex.put(name, index);
            indexToName.add(name);
        }
    }

    public void addEdge(String from, String to) {
        if (!nameToIndex.containsKey(from) || !nameToIndex.containsKey(to)) {
            throw new IllegalArgumentException("Node not found");
        }
        int fromIndex = nameToIndex.get(from);
        int toIndex = nameToIndex.get(to);
        adj.get(fromIndex).add(toIndex);
    }

    public int getEdgeWeight(String from, String to) {
        if (!nameToIndex.containsKey(from) || !nameToIndex.containsKey(to)) {
            throw new IllegalArgumentException("Node not found");
        }
        int fromIndex = nameToIndex.get(from);
        int toIndex = nameToIndex.get(to);
        int count = 0;
        for (int neighborIndex : adj.get(fromIndex)) {
            if (neighborIndex == toIndex) {
                count++;
            }
        }
        return count;
    }


    public Iterable<String> adj(String name) {
        if (!nameToIndex.containsKey(name)) {
            throw new IllegalArgumentException("Node not found");
        }
        List<String> neighbors = new ArrayList<>();
        int index = nameToIndex.get(name);
        for (int neighborIndex : adj.get(index)) {
            neighbors.add(indexToName.get(neighborIndex));
        }
        return neighbors;
    }

    public void create_graph(String content){
        String[] words = content.split(" ");  // 分隔字符串

        for (String word : words) {
            addNode(word);
        }
        for(int i =0; i<words.length-1; i++){
            addEdge(words[i],words[i+1]);
        }
    }

    // 返回 indexToName 的不可变副本
    public List<String> getNode(){
        return new ArrayList<>(indexToName);
    }

    // 返回 nameToIndex 的不可变副本
    public Map<String, Integer> getnameToIndex(){
        return new HashMap<>(nameToIndex);
    }

    // 返回 adj 的深拷贝
    public List<List<Integer>> getAdj(){
        List<List<Integer>> adjCopy = new ArrayList<>();
        for (List<Integer> neighbors : adj) {
            adjCopy.add(new ArrayList<>(neighbors));
        }
        return adjCopy;
    }

    public static void main(String[] args) {
        GraphDemo graph = new GraphDemo(5);
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "C"); // 添加一条权重为2的边

        System.out.println("Weight from A to B: " + graph.getEdgeWeight("A", "B")); // 输出1
        System.out.println("Weight from B to C: " + graph.getEdgeWeight("B", "C")); // 输出2
    }

}
