//1st
import java.util.*;

class Node {
    Queue<Integer> queue = new LinkedList<>();
    int queueSize, packetsDropped = 0;

    Node(int size) { this.queueSize = size; }

    void sendPacket(int packetId) {
        if (queue.size() < queueSize) queue.add(packetId);
        else packetsDropped++;
    }

    void receivePacket() { if (!queue.isEmpty()) queue.poll(); }
}

public class SimpleNetwork {
    public static void main(String[] args) {
        int numNodes = 3, queueSize = 2, packetsToSend = 100;
        Node[] nodes = new Node[numNodes];
        Random rand = new Random();

        for (int i = 0; i < numNodes; i++) nodes[i] = new Node(queueSize);

        for (int i = 0; i < packetsToSend; i++) {
            nodes[rand.nextInt(numNodes)].sendPacket(i);
            for (Node node : nodes) if (rand.nextBoolean()) node.receivePacket();
        }

        for (int i = 0; i < numNodes; i++)
            System.out.println("Node " + i + " dropped packets: " + nodes[i].packetsDropped);
    }
}
