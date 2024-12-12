import java.util.LinkedList;
import java.util.Queue; 
import java.util.Random; 

class Node { 
    Queue<Integer> queue = new LinkedList<>(); 
    int queueSize, packetsDropped = 0; 

    Node(int size) { 
        queueSize = size; 
    } 

    void sendPing(int packetId) { 
        if (queue.size() < queueSize) { 
            queue.add(packetId); 
        } else { 
            packetsDropped++; 
        } 
    } 

    void receivePing() { 
        if (!queue.isEmpty()) queue.poll(); 
    } 
} 

public class NetworkSimulation { 
    public static void main(String[] args) { 
        int numNodes = 6, queueSize = 2, pingsToSend = 300; 
        Node[] nodes = new Node[numNodes]; 
        Random rand = new Random(); 

        for (int i = 0; i < numNodes; i++) 
            nodes[i] = new Node(queueSize); 

        for (int i = 0; i < pingsToSend; i++) { 
            int sender = rand.nextInt(numNodes); 
            nodes[sender].sendPing(i); 
        } 

        for (Node node : nodes) { 
            for (int i = 0; i < 10; i++) { 
                node.receivePing(); 
            } 
        } 

        for (int i = 0; i < numNodes; i++)  
            System.out.println("Node " + i + " dropped packets: " + nodes[i].packetsDropped); 
    } 
}