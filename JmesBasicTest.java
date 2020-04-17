import com.fasterxml.jackson.databind.JsonNode;

public class JmesBasicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		PackageTypes pkg = new PackageTypes();
		JsonNode node1, node2, node3;
		node1 = pkg.Basic1();
		node2 = pkg.Basic2();
		node3 = pkg.Basic3();
		System.out.println(node1);
		System.out.println("\n");
		System.out.println("Max temp "+node2);
		System.out.println("\n");
		System.out.println(node3);
		System.out.println("\n");
	}

}
