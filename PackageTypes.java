import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.burt.jmespath.Expression;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.RuntimeConfiguration;
import io.burt.jmespath.function.FunctionRegistry;
import io.burt.jmespath.jackson.JacksonRuntime;

/**
 * 
 */

/**
 * @author asus
 *
 */
public class PackageTypes {

	FunctionRegistry defaultFunctions = FunctionRegistry.defaultRegistry();
	FunctionRegistry customFunctions = defaultFunctions.extend(new MaaxFunction());
	RuntimeConfiguration configuration = new RuntimeConfiguration.Builder()
            .withFunctionRegistry(customFunctions)
            .build();
	
	public JsonNode Basic1() {
		
		
		JsonNode input, result= null;
    	String jsonString = "{\"temperature\": 22.6,\"humidity\": 41,\"light\": 39}";
    	JmesPath<JsonNode> jmespath = new JacksonRuntime(configuration);
    	Expression<JsonNode> expression = jmespath.compile("maax(temperature)");
    	ObjectMapper mapper = new ObjectMapper();
    	try {
	    	input =  mapper.readTree(jsonString);
	    	result = expression.search(input);
	    	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return result;
	}
	
	public JsonNode Basic2() {
	
		
		JsonNode input, result= null;
    	String jsonString = "{\"firmwareType\": \"SenlabT\",\r\n" + 
    			"    \"id\": \"datalog_transmission\",\r\n" + 
    			"    \"measures\": [\r\n" + 
    			"        {\r\n" + 
    			"            \"id\": \"temperature\",\r\n" + 
    			"            \"timestamp\": 1580980445688,\r\n" + 
    			"            \"value\": 11.625,\r\n" + 
    			"            \"time\": \"2020-02-06T09:14:05.688Z\"\r\n" + 
    			"        },\r\n" + 
    			"        {\r\n" + 
    			"            \"id\": \"temperature\",\r\n" + 
    			"            \"timestamp\": 1580980545688,\r\n" + 
    			"            \"value\": 11.625,\r\n" + 
    			"            \"time\": \"2020-02-06T09:15:45.688Z\"\r\n" + 
    			"        },\r\n" + 
    			"        {\r\n" + 
    			"            \"id\": \"temperature\",\r\n" + 
    			"            \"timestamp\": 1580980645688,\r\n" + 
    			"            \"value\": 11.6875,\r\n" + 
    			"            \"time\": \"2020-02-06T09:17:25.688Z\"\r\n" + 
    			"        },\r\n" + 
    			"        {\r\n" + 
    			"            \"id\": \"battery_current_level\",\r\n" + 
    			"            \"timestamp\": 1580980695688,\r\n" + 
    			"            \"value\": 30,\r\n" + 
    			"            \"time\": \"2020-02-06T09:18:15.688Z\"\r\n" + 
    			"        }\r\n" + 
    			"    ],\r\n" + 
    			"    \"parameters\": [],\r\n" + 
    			"    \"events\": []}";
    	JmesPath<JsonNode> jmespath = new JacksonRuntime(configuration);
    	Expression<JsonNode> expression = jmespath.compile("maax(measures[?id == 'temperature'].value)");
    	ObjectMapper mapper = new ObjectMapper();
    	try {
	    	input =  mapper.readTree(jsonString);
	    	result = expression.search(input);
	    	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return result;
		
	}
	public JsonNode Basic3() {
		JsonNode input, result= null;
    	String jsonString = "{\"EndPoint\": 0,\r\n" + 
    			"    \"Report\": \"Standard\",\r\n" + 
    			"    \"CommandID\": \"ReportAttributes\",\r\n" + 
    			"    \"ClusterID\": \"TIC_CBE\",\r\n" + 
    			"    \"AttributeID\": \"Attribute_0\",\r\n" + 
    			"    \"AttributeType\": \"ByteString\",\r\n" + 
    			"    \"Data\": {\r\n" + 
    			"        \"TICFieldList\": {\r\n" + 
    			"            \"DescHeader\": {\r\n" + 
    			"                \"Obsolete\": \"No\",\r\n" + 
    			"                \"Report\": \"Standard\",\r\n" + 
    			"                \"PresentField\": \"DescVarBitfield\",\r\n" + 
    			"                \"SizeOf\": 0\r\n" + 
    			"            },\r\n" + 
    			"            \"BitField\": [0, 0, 0, 0, 1, 248, 0],\r\n" + 
    			"            \"BBRHCJB\": 123456789,\r\n" + 
    			"            \"BBRHPJB\": 999999999,\r\n" + 
    			"            \"BBRHCJW\": 999999999,\r\n" + 
    			"            \"BBRHPJW\": 999999999,\r\n" + 
    			"            \"BBRHCJR\": 999999999,\r\n" + 
    			"            \"BBRHPJR\": 999999999\r\n" + 
    			"        }\r\n" + 
    			"    },\r\n" + 
    			"    \"Cause\": []}";
    	JmesPath<JsonNode> jmespath = new JacksonRuntime();
    	Expression<JsonNode> expression = jmespath.compile("to_array(@) | [?CommandID == 'ReportAttributes' && AttributeID == 'Attribute_0'].Data.TICFieldList.BBRHCJB");
    	ObjectMapper mapper = new ObjectMapper();
    	try {
	    	input =  mapper.readTree(jsonString);
	    	result = expression.search(input);
	    	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return result;
		
	}

}
