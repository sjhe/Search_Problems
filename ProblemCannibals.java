import java.util.HashSet;
import java.util.Set;

public class ProblemCannibals extends Problem {
	
    static final int cannL = 0;
    static final int missL = 1;
    static final int boatL = 2;
    static final int cannR = 3;
    static final int missR = 4;
    static final int boatR = 5;
    
	boolean goal_test(Object state) {
        StateCannibals can_state = (StateCannibals) state;
        
        if (can_state.canArray[cannR] == 3 && can_state.canArray[missR] == 3 )
            return true;
        else return false;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateCannibals can_state = (StateCannibals) state;
        
        //Let's create without any constraint, then remove the illegal ones
        StateCannibals successor_state;
        //one cannibal only from left to right
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 1;
        successor_state.canArray[cannR] += 1;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one cannibal only from right to left
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannR] -= 1;
        successor_state.canArray[cannL] += 1;
        successor_state.canArray[boatR] -= 1;
        successor_state.canArray[boatL] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two cannibals from left to right
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 2;
        successor_state.canArray[cannR] += 2;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two cannibals from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannR] -= 2;
        successor_state.canArray[cannL] += 2;
        successor_state.canArray[boatR] -= 1;
        successor_state.canArray[boatL] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one missionary only from left to right 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missL] -= 1;
        successor_state.canArray[missR] += 1;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        
        //one missionary only from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missR] -= 1;
        successor_state.canArray[missL] += 1;
        successor_state.canArray[boatR] -= 1;
        successor_state.canArray[boatL] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two missionaries from left to right 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missL] -= 2;
        successor_state.canArray[missR] += 2;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two missionaries from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missR] -= 2;
        successor_state.canArray[missL] += 2;
        successor_state.canArray[boatR] -= 1;
        successor_state.canArray[boatL] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one cannibal and one missionary from left to right 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 1;
        successor_state.canArray[cannR] += 1;

        successor_state.canArray[missL] -= 1;
        successor_state.canArray[missR] += 1;

        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one cannibal and one missionary from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannR] -= 1;
        successor_state.canArray[cannL] += 1;

        successor_state.canArray[missR] -= 1;
        successor_state.canArray[missL] += 1;

        successor_state.canArray[boatR] -= 1;
        successor_state.canArray[boatL] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        return set;
    }

    private boolean isValid(StateCannibals state)
    {   
        //Checking to see if any element of the array is negative 
        for (int i=0; i<6; i++){
            if (state.canArray[i] < 0) return false;
        }
        //Checking to see if the numbers of cannibals, missionaries, and boat 
        //are more then 3,3,1 respectively
        int num_cannibals    = state.canArray[cannL] + state.canArray[cannR],
            num_missionaries = state.canArray[missL] + state.canArray[missR],
            num_boat         = state.canArray[boatL] + state.canArray[boatR] ;

        if ( num_cannibals != 3  || num_missionaries != 3 || num_boat != 1) return false; 

        //Now, checking if cannibals out number missionaries
        if( (state.canArray[cannL] > state.canArray[missL]) && state.canArray[missL] != 0 || ( (state.canArray[cannR] > state.canArray[missR]) &&  state.canArray[missR] != 0 ) ){
            return false;
        } 
        return true;
    }
	
    // Step cost 
	double step_cost(Object fromState, Object toState) { return 1; }


    // Heuristic function
	public double h(Object state) { 
        StateCannibals can_state = (StateCannibals) state;
        // System.out.println(can_state.toString());
        int totalL = can_state.canArray[cannL] + can_state.canArray[missL];
        return totalL/2; 
        // return 0;
    }


	public static void main(String[] args) throws Exception {
		ProblemCannibals problem = new ProblemCannibals();
		int[] canArray = {3,3,1,0,0,0};
		problem.initialState = new StateCannibals(canArray); 
		
		Search search  = new Search(problem);
		




        System.out.println("\n\nTreeSearch------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
        System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstTreeSearch());
        System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());


        System.out.println("\n\nGraphSearch------------------------");
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
        System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());


        System.out.println("\n\nIterativeDeepening----------------------");
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
        System.out.println("\n\n");


	}
}
