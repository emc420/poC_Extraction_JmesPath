import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.DoubleNode;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.function.FunctionArgument;

public class MaaxFunction extends BaseFunction{
	
	public MaaxFunction() {
	    super(ArgumentConstraints.typeOf(JmesPathType.ARRAY, JmesPathType.NUMBER));
	  }
	
	@Override
	  protected <T> T callFunction(Adapter<T> runtime, List<FunctionArgument<T>> arguments) {
		double s=0;
		T value = arguments.get(0).value();
		if(value.getClass() == ArrayNode.class) {
			s = max((ArrayNode)value);		
		}else if(value.getClass() == DoubleNode.class) {
			s = runtime.toNumber(value).doubleValue();;
		}
	    
	    return runtime.createNumber(s);
	  }
	

	public double max(ArrayNode x) {
		
		float m=0;
		for (int i=0;i<x.size();i++) {
			if(m<x.get(i).floatValue()) {
				m = x.get(i).floatValue();			
			}
		}
		return m;
	}

}
