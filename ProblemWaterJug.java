import java.util.HashSet;
import java.util.Set;

public class ProblemWaterJug extends Problem{

	static final int tweleveGalJug = 0;
    static final int eightGalJug   = 1;
    static final int threeGalJug   = 2;

    boolean goal_test(Object state) {
        StateWaterJug jug_state = (StateWaterJug) state;
        
        if (jug_state.jugArray[tweleveGalJug] == 1 || jug_state.jugArray[eightGalJug] == 1 || jug_state.jugArray[threeGalJug] ==1 )
            return true;
        else return false;
	}

	   Set<Object> getSuccessors(Object state){
        Set<Object> set = new HashSet<Object>();
        StateWaterJug jug_state = (StateWaterJug) state;
        
        //Let's create without any constraint, then remove the illegal ones
        StateWaterJug successor_state;


        //fill the 8 gallon jug
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (8 - successor_state.jugArray[eightGalJug]);
        successor_state.jugArray[eightGalJug] = 8;
        if(isValid(successor_state)) set.add(successor_state);

        //fill the 12 gallon jug from 8 gallon
        // 8 -> 12 and 12 gallon is full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (12 - successor_state.jugArray[tweleveGalJug]) ;
        successor_state.jugArray[eightGalJug]  -= (12 - successor_state.jugArray[tweleveGalJug]);
        successor_state.jugArray[tweleveGalJug] = 12;
        if (isValid(successor_state)) set.add(successor_state);

        // 8 -> 12 and 12 gallon is not full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[eightGalJug];
        successor_state.jugArray[tweleveGalJug] += successor_state.jugArray[eightGalJug];
        successor_state.jugArray[eightGalJug]   -= successor_state.jugArray[eightGalJug] ;
        if (isValid(successor_state)) set.add(successor_state);


        //fill the 3 gallon jug from 8 gallon
        // 8 -> 3 and 3 gallon is full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (3 - successor_state.jugArray[threeGalJug]) ;
        successor_state.jugArray[eightGalJug] -= (3 - successor_state.jugArray[threeGalJug]) ;
        successor_state.jugArray[threeGalJug] = 3;
        if (isValid(successor_state)) set.add(successor_state);

        // 8 -> 3 and 3 gallon is not full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[eightGalJug];
        successor_state.jugArray[threeGalJug] += successor_state.jugArray[eightGalJug];
        successor_state.jugArray[eightGalJug] -=  successor_state.jugArray[eightGalJug] ;
        if (isValid(successor_state)) set.add(successor_state);


        //empty the 8 gallon jug to the ground
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[eightGalJug];
        successor_state.jugArray[eightGalJug] = 0;
        if (isValid(successor_state)) set.add(successor_state);

        // END 8 gallon jug 

        //fill the 3 gallon jug
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (3 - successor_state.jugArray[threeGalJug]);
        successor_state.jugArray[threeGalJug] = 3;
        if(isValid(successor_state)) set.add(successor_state);

        //empty the 3 gallon jug to the ground
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[threeGalJug] = 0;
        if (isValid(successor_state)) set.add(successor_state);
        
        //fill the 12 gallon jug from 3 gallon jug
        // 3 -> 12 and 12 gallon is full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (12 - successor_state.jugArray[tweleveGalJug]) ;
        successor_state.jugArray[threeGalJug]  -= (12 - successor_state.jugArray[tweleveGalJug]);
        successor_state.jugArray[tweleveGalJug] = 12;
        if (isValid(successor_state)) set.add(successor_state);

        //fill the 12 gallon jug from 3 gallon jug
        // 3 -> 12 and 12 gallon is not full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[tweleveGalJug] += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[threeGalJug]  = 0;
        if (isValid(successor_state)) set.add(successor_state);
        //fill the 12 gallon jug from 3 gallon jug
        // 3 -> 12 and 12 gallon is full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (12 - successor_state.jugArray[tweleveGalJug]) ;
        successor_state.jugArray[threeGalJug]  -= (12 - successor_state.jugArray[tweleveGalJug]);
        successor_state.jugArray[tweleveGalJug] = 12;
        if (isValid(successor_state)) set.add(successor_state);

        //fill the 12 gallon jug from 3 gallon jug
        // 3 -> 12 and 12 gallon is not full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[tweleveGalJug] += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[threeGalJug]  = 0;
        if (isValid(successor_state)) set.add(successor_state);

        //fill the 8 gallon jug with the water from 3 gallon
        //3 -> 8 and 8 is full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (8 - successor_state.jugArray[eightGalJug]);
        successor_state.jugArray[threeGalJug] -= (8 - successor_state.jugArray[eightGalJug]);
        successor_state.jugArray[eightGalJug] = 8;
        if (isValid(successor_state)) set.add(successor_state);

        // 3 -> 8 and 8 gallon is not full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[eightGalJug] += successor_state.jugArray[threeGalJug];
        successor_state.jugArray[threeGalJug] = 0;
        if (isValid(successor_state)) set.add(successor_state);
        // END 3 gallon jug 


        //fill the 12 gallon jug
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (12 - successor_state.jugArray[tweleveGalJug]);
        successor_state.jugArray[tweleveGalJug] = 12;
        if(isValid(successor_state)) set.add(successor_state);

        //empty the 12 gallon jug to the ground
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[tweleveGalJug];
        successor_state.jugArray[tweleveGalJug] = 0;
        if (isValid(successor_state)) set.add(successor_state);

        //fill the 3 gallon jug with the water from 12 gallon
        // 12 -> 3 and 3 gallon will be full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (3 - successor_state.jugArray[threeGalJug]);
        successor_state.jugArray[tweleveGalJug] -=  (3 - successor_state.jugArray[threeGalJug]);
        successor_state.jugArray[threeGalJug]   = 3;
        if (isValid(successor_state)) set.add(successor_state);

        //fill the 3 gallon jug with the water from 12 gallon
        // 12 -> 3 and 3 gallon will not be full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[tweleveGalJug] ;
        successor_state.jugArray[threeGalJug]   += successor_state.jugArray[tweleveGalJug];
        successor_state.jugArray[tweleveGalJug] -= successor_state.jugArray[tweleveGalJug];
        if (isValid(successor_state)) set.add(successor_state);

        //fill the 8 gallon jug with the water from 12 gallon
        // 12 -> 8 and 8 gallon is full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += (8 - successor_state.jugArray[eightGalJug]) ;
        successor_state.jugArray[tweleveGalJug] -=  (8 - successor_state.jugArray[eightGalJug]) ;
        successor_state.jugArray[eightGalJug]   = 8;
        if (isValid(successor_state)) set.add(successor_state);

        // 12 -> 8 and 8 gallon is not full
        successor_state = new StateWaterJug(jug_state);
        successor_state.cost += successor_state.jugArray[tweleveGalJug];
        successor_state.jugArray[eightGalJug]   += successor_state.jugArray[tweleveGalJug];
        successor_state.jugArray[tweleveGalJug] -=  successor_state.jugArray[tweleveGalJug] ;
        if (isValid(successor_state)) set.add(successor_state);

        // END 12 gallon jug 
        return set;
    }


	double step_cost(Object fromState, Object toState){
        StateWaterJug from_State = (StateWaterJug) fromState;
        StateWaterJug to_State = (StateWaterJug) toState;
        int cost = to_State.cost;
        // System.out.println("From State = " + from_State.toString());
        // System.out.println("To State = " + to_State.toString());
        // System.out.println("cost = " + to_State.cost);
        // if(cost > 0)
        //     return cost;
        // else return 1;  // # of galon used for move
        return cost;
	};
	public double h(Object state){
		return 0.0;
	};

	private boolean isValid(StateWaterJug state)
    {   
        //Checking to see if any element of the array is negative 
        for (int i=0; i<3; i++){
            if (state.jugArray[i] < 0) return false;
        }

        //Checking to see if the numbers of gallons of each jugs exceed its limit
        if ( state.jugArray[tweleveGalJug] > 12  || state.jugArray[eightGalJug] > 8 || state.jugArray[threeGalJug] > 3 || state.cost == 0) return false; 

        return true;
    }
	public static void main(String[] args) throws Exception {
		ProblemWaterJug problem = new ProblemWaterJug();
		int[] jugArray = { 0,0,0};
		problem.initialState = new StateWaterJug(jugArray); 
		
		Search search  = new Search(problem);
		
        System.out.println("\n\nTreeSearch------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());

        System.out.println("\n\nGraphSearch------------------------");
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());

        System.out.println("\n\nIterativeDeepening----------------------");
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
        System.out.println("\n\n");


	}
}
