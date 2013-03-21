import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA4
 * Date: 3/21/13
 * Time: 12:28 AM
 * Java Class: PACKAGE_NAME
 */
class GraphException extends RuntimeException{

    public GraphException (String word){
         super(word);
    }
   }

class Vertex{
    public String           word;             //Word at this Node
    public List<Edge> adj;             //List of  Adjacent vertices
    public Vertex           prev;            //Previous vertex on shortest path
    public int              scratch;        //Extra variable
    public double           dist;


    public Vertex (String word){
        this.word = word;
        adj = new LinkedList<Edge>();
        reset();

    }

    public void reset (){
        prev = null;
        scratch = 0;
        dist = TestGraph.INFINITY;
    }
}
class Edge {
    public Vertex dest; //Second vertex in Edge
    public double cost;


    public Edge (Vertex d, double c){
        dest = d;
        cost = c;
    }

}

public class TestGraph {
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );

    public int getVertexMapSize(){
        return vertexMap.size();
    }

    //Add an edge to the graph

    public void addEdge (String sourceWord, String destinationWord, double cost){
        Vertex v = getVertex(sourceWord);
        Vertex w = getVertex(destinationWord);
        v.adj.add(new Edge (w, cost));
    }

    public void printPath (String destWord){
        Vertex w = vertexMap.get(destWord);
        if ( w == null){
            throw new NoSuchElementException("Destination vertex not found");
        } else if (w.dist == INFINITY){
            System.out.println(destWord + " is unreachable.");
        }

        else {
            System.out.print( "( Cost is: " + w.dist + ") ");
            printPath( w );
            System.out.println();
        }
    }

    /*
        If vertexWord is not present, add it to the vertexMap.
        In either case, return the vertex
     */
      private Vertex getVertex (String vertexWord){
          Vertex v = vertexMap.get(vertexWord);
          if ( v == null){
              v = new Vertex(vertexWord);
              vertexMap.put(vertexWord, v);
          }
          return v;
      }
        /*
        Recursive routine to rpint the shortest path to dest after running shortest path algorithm.
        The path is known to exist
         */
    private void printPath (Vertex dest){
        if (dest.prev != null){
            printPath(dest.prev);
            System.out.print(" to ");
        }

        System.out.print(dest.word);
    }

    private void clearAll(){
        for (Vertex v : vertexMap.values()){
            v.reset();
        }
    }

    /*
    Single source unweighted shortest path algorithm

     */

    public void unweighted (String sourceWord){
        clearAll();

        Vertex start = vertexMap.get(sourceWord);

        if (start == null){
            throw new NoSuchElementException("Start vertex not found");
        }

        Queue <Vertex> q = new LinkedList<Vertex>();
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

}
