public class StateWaterJug
{    
    int jugArray[];
    
    public StateWaterJug(int[] jugArray) { this.jugArray = jugArray; }
    
    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StateWaterJug(StateWaterJug state) {
    	jugArray = new int[4];
        for(int i=0; i<3; i++) 
            this.jugArray[i] = state.jugArray[i];
    }
    
    public boolean equals(Object o)
    {
        StateWaterJug state = (StateWaterJug) o;
        
        for (int i=0; i<3; i++)
            if (this.jugArray[i] != state.jugArray[i])
                return false;
        
        return true;
    }
  
    
    public String toString()
    {
        String ret = "";
        for (int i=0; i<3; i++)
            ret += " " + this.jugArray[i];
        return ret;
    }
}