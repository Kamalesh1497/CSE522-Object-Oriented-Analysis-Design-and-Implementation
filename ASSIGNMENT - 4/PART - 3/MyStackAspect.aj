public aspect MyStackAspect {

	int old_valueStack;
	
	int topStack;
	int old_topStack;
	
	int sizeStack;
	int old_sizeStack;
	
	//pointcut for the push function
	pointcut call_push(int n, MyStack mystackObj) : 
    	call(void MyStack.push(int)) && args(n) && target(mystackObj);
	
	//in contract for push @requires true
	before(int valueStack, MyStack mystackObj) : call_push(valueStack , mystackObj) {
		
		old_valueStack = valueStack; // the cuurent value to push in stack
		
		old_topStack = mystackObj.top; //current top before push operation
		
		old_sizeStack = mystackObj.size; //current top before push operation
    	
    	
      	assert(precondition_push()); // assert the precondition for push
      	
      	assert(invariant()); // assert the invariant for before push 
      	
    }
	
	// in contract the require for push is  @requires true
	boolean precondition_push() {
		
		return true;
		
	}
	
	//in contract the ensure for push is @ensures size == 1 + old_size && top == old_v
	after(int valueStack , MyStack mystackObj) : call_push(valueStack , mystackObj) {
		
		topStack = mystackObj.top; // current top of the stack after the push operation
		
		sizeStack = mystackObj.size; //current size of the stack after the push operation
		
    	assert(postcondition_push(valueStack)); // assert the post condition after push
    	
    	assert(invariant()); // assert the invariant after push
    }
	
	//after push the top and size should be updated
	//the top should be equal to the new value and 
	//the size should be 1 more than the old size
    boolean postcondition_push(int valueStack) {
    	
       return topStack == valueStack  && sizeStack == old_sizeStack + 1;
       
	} 
    
    //pointcut for the pop function
    pointcut call_pop(MyStack mystackObj) : 
    	call(int MyStack.pop()) && target(mystackObj);
	
    // in contract the require for pop is @requires size >= 1
	before(MyStack mystackObj) : call_pop(mystackObj) {
		
		old_sizeStack = mystackObj.size; // size before the pop operation
		
    	assert(precondition_pop()); // assert pre condition before pop operation
    	
    	assert(invariant()); // assert invariant before pop operation
    }
	
	//pre condition function for pop operation.
	boolean precondition_pop() {
		
		if( sizeStack >= 1 ) {
			
			return true;
		
		}
	
			return false;
	}
	
	//in contract the ensure for pop is @ensures  size == old_size - 1 && result == old_top
	after(MyStack mystackObj) returning(int top) : call_pop(mystackObj) {
		
		topStack = mystackObj.top; //current top after the pop operation
		
		sizeStack = mystackObj.size; // current size after the pop operation
		
    	assert(postcondition_pop(top)); // assert the post condition after pop operation
    	
    	assert(invariant()); // assert invariant after pop operation
    
	}
	
	// post condition function
	// result should be equal to the top
	// and the size should be 1 less than the old size.
    boolean postcondition_pop(int result) {
    	
       return  topStack == result && sizeStack == old_sizeStack-1;
       
	} 
	
    // invriant function
    // the size of stack should be greater than or equal to 0.
    boolean invariant() {
    	
    	if(sizeStack >= 0) {
    		
    	return true;
    	
    	}
    	
    	return false;
    }
}