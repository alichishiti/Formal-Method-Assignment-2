package sat_solver;
public class SAT_solver {
    public static void main(String[] args) {
        
        MakeCNF a = new MakeCNF();
        String b = "~~p&~(q&~(p&~r)&s|~~p)|~s";
        System.out.println(a.makeCNF(b));    
    }
}