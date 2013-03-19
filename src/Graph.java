import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/19/13
 * Time: 12:08 PM
 * Java Class: PACKAGE_NAME
 */
public class Graph {

    public static final double INFINITY = Double.MAX_VALUE;
    /*
    Add a edge to the graph
    */

    public void addEdge (String sourceName, String destName, double cost){
         Vertex v = getVertex(sourceName);
         Vertex w = getVertex(destName);
         v.adj.add(new Edge(w, cost));
    }
       /*
            Driver routine to handle unreachables and print the total cost.
            It calls recursive routine to print the shortest path to
            destNode after ta shortest path algorithm has run.

        */
    public void printPath(String destName){
            Vertex w = vertexMap.get(destName);
            if (w == null){
                throw new NoSuchElementException();
            }else if (w.dist == INFINITY){
                System.out.println(destName + " is unreachable");
            }else {
                System.out.print(" (Cost is " + w.dist + ") ");
                printPath(w);
                System.out.println();

            }
    }

    public void unweighted(String startName){
         clearAll();

        Vertex start =  vertexMap.get(startName);
        if (start == null){
            throw new NoSuchElementException("Start vertex not found");
        }

        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        start.dist = 0;

         while ( !q.isEmpty()){
             Vertex v = q.remove();

             for (Edge e : v.adj){
                 Vertex w = e.dest;

                 if (w.dist == INFINITY){
                     w.dist = v.dist +1;
                     w.prev = v;
                     q.add(w);
                 }
             }
         }
    }

    public void dijkstra(String startName){

    }

    public void negative(String startName){

    }

    public void acyclic (String startName){

    }

    /**
     *  If vertexName is not present, add it to vertexMap.
     *  In either case, return the Vertex
     */
    private Vertex getVertex (String vertexName){
        Vertex v = vertexMap.get(vertexName);

        if (v == null){
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }

        return v;
    }

     /*
        Recursive routine to print the shortest path to dest
        after running shortest path algorithm.  The path
        is known to exist

      */
    private void printPath(Vertex dest){
        if (dest.prev !=null){
            printPath(dest.prev);
            System.out.println( " to ");

        }
        System.out.println(dest.name);
    }

    private void clearAll(){
            for (Vertex v : vertexMap.values()){
                v.reset();
            }
    }


    private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

    class GraphException extends RuntimeException{
        public GraphException(String name){
            super(name);
        }

    }

    class Edge{
        public Vertex dest;
        public double cost;

        public Edge(Vertex d, double c){
            dest = d;
            cost = c;
        }
    }

    class Vertex{
        public String     name; //Vertex name
        public List<Edge> adj;  // Adjacent vertices
        public double     dist; // Cost
        public Vertex     prev; // Previous vertex on the shortest path
        public int        scratch;  //Extra variable

        public Vertex (String nm){
            name = nm;
            adj = new LinkedList<Edge>();
            reset();
        }

        public void reset (){
            dist = Graph.INFINITY;
            prev = null;
            //pos = null;
            scratch = 0;
        }
    }

}
