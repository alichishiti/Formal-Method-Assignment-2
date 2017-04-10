package sat_solver;
public class SAT_solver {
    public static void main(String[] args) {
        
        String b = "~p|q";
        MakeCNF a = new MakeCNF(b);
        //System.out.print(a.makeCNF());
        ClockTower chishti= new ClockTower(b);
        chishti.extractPropositions();
        chishti.MapPropositionValues();
      
        
//        a.Bijection();
//        a.Implication();
        
        //System.out.println(a.makeCNF(b));    
    }
}