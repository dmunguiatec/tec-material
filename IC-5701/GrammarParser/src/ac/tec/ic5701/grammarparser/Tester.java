package ac.tec.ic5701.grammarparser;

import java.io.FileReader;
import java.util.List;

public class Tester {
    public static void main(String[] args) throws Exception {
        GParser parser = new GParser();
        List<Rule> symbols = parser.parse(new FileReader("expresiones.gramatica"));
        System.out.println("symbols = " + symbols);
    }
}
