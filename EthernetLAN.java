//3
import java.util.LinkedList;
import java.util.Queue; 
import java.util.Random; 
 
class Node { 
    Queue<Integer> queue = new LinkedList<>(); 
    int queueSize, packetsDropped = 0; 
 
    Node(int size) { this.queueSize = size; } 
 
    void sendPacket(int packetId) { 
        if (queue.size() < queueSize) { 
            queue.add(packetId); 
        } else { 
            packetsDropped++; 
        } 
    } 
 
    void receivePacket() { 
        if (!queue.isEmpty()) queue.poll(); 
    } 
 
    int getDroppedPackets() { return packetsDropped; } 
} 
 
public class EthernetLAN { 
    public static void main(String[] args) { 
        int numNodes = 5, queueSize = 3, packetsToSend = 50; 
        Node[] nodes = new Node[numNodes]; 
        Random rand = new Random(); 
 
        // Initialize nodes 
        for (int i = 0; i < numNodes; i++) nodes[i] = new Node(queueSize); 
 
        // Simulate sending packets 
        for (int i = 0; i < packetsToSend; i++) { 
            int sender = rand.nextInt(numNodes); 
            nodes[sender].sendPacket(i); // Send a packet 
        } 
 
        // Simulate packet processing 
        for (Node node : nodes) { 
            for (int j = 0; j < 5; j++) node.receivePacket(); // Process packets 
        } 
 
        // Output dropped packets for each node 
        System.out.println("Dropped packets per node:"); 
        for (int i = 0; i < numNodes; i++) { 
            System.out.println("Node " + i + ": " + nodes[i].getDroppedPackets()); 
        } 
 
        // Congestion window simulation (for plotting) 
        System.out.println("\nCongestion Window:"); 
        for (int i = 0; i < numNodes; i++) { 
            System.out.println("Node " + i + " congestion window: " + (queueSize - 
nodes[i].getDroppedPackets())); 
        } 
    } 
} 