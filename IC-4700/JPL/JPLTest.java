import jpl.Query;

public class JPLTest {

   public static void main(String[] args) {
      Query q;
      q = new Query("consult('gen.pl')");
      System.out.println(q.hasSolution());
      q = new Query("ancestro(X, pam)");
      System.out.println(q.hasSolution());

      while (q.hasMoreElements()) {
         System.out.println(q.nextElement());
      }
   }
}