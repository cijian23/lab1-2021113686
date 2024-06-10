import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackTest {

    private static GraphDemo graph;

    @BeforeAll
    static void setUp() {
        String content = Main.readFile("shuru.txt");
        // 初始化图的结构，根据你的需求填充图中的数据
        graph = new GraphDemo(100);
        graph.create_graph(content);
        // 添加其他必要的边
    }

    @Test
    void testBridgeWordsExist() {
        String expected = "The bridge words from \"cat\" to \"happy\" are: was.";
        String actual = Main.queryBridgeWords(graph,"cat", "happy");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testWordNotInGraph() {
        String expected = "No \"hh\" in the graph!";
        String actual = Main.queryBridgeWords(graph,"hh", "cat");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testBothWordsNotInGraph() {
        String expected = "No \"ha\" and \"ppy\" in the graph!";
        String actual = Main.queryBridgeWords(graph,"ha", "ppy");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testNoBridgeWords() {
        String expected = "No bridge words from \"the\" to \"on\"!";
        String actual = Main.queryBridgeWords(graph,"the", "on");
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}
