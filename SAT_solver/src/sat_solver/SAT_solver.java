package sat_solver;
public class SAT_solver {
    public static void main(String[] args) {
        
        String b = "p|(q&r)";
        MakeCNF a = new MakeCNF(b);
        System.out.print(a.makeCNF());
        
//        a.Bijection();
//        a.Implication();
        
        //System.out.println(a.makeCNF(b));    
    }
}