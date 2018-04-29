package diag.assignment.mediator.rules;

import diag.assignment.mediator.rules.jaxb.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    08/05/14 01:43
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class TestAssignmentRules {

    public static void main(String[] args) throws JAXBException, IllegalAccessException {

        JAXBContext jaxbContext = JAXBContext.newInstance(AssignmentRules.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File f = new File("C:\\Users\\golaniz\\Documents\\Izik\\Java\\Projects\\123Test\\static_content\\diag\\assignment\\mediator\\rules\\AssignmentRules.xml");
        Object obj = unmarshaller.unmarshal(f);

        AssignmentRules mars = (AssignmentRules) obj;
        List<AssignmentRule> rules = mars.getAssignmentRule();
        AssignmentRule mar = createNewMAR();
        rules.add(mar);
        rules.sort(new Comparator<AssignmentRule>() {
            @Override
            public int compare(AssignmentRule o1, AssignmentRule o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(mars, f);

        //
        //for (AssignmentRule rule : rules) {
        //    System.out.println(rule.getName());
        //}
        //
        //System.out.println( ReflectionBasedSerializer.toXmlString(obj, 4, true) );


    }

    private static AssignmentRule createNewMAR() {
        AssignmentRule mar = new AssignmentRule();
        mar.setName("03-Three");
        mar.setPriority(150);
        Condition condition = createCondition("Fire", "App2", "SomethingElse");
        mar.setCondition(condition);
        Results matches = new Results();
        List<String> matchList = matches.getResult();
        matchList.add("GeneralDevMedPool");
        matchList.add("GeneralDevMedPool2");
        mar.setResults(matches);
        return mar;
    }

    private static Condition createCondition(String pod, String pname, String env) {
        Condition condition = new Condition();
        List<PropertyCondition> conds = condition.getPropertyCondition();
        conds.add(createPropertyCondition("Pod", pod));
        conds.add(createPropertyCondition("PName", pname));
        conds.add(createPropertyCondition("Env", env));
        return condition;
    }

    private static PropertyCondition createPropertyCondition(String name, String value) {
        PropertyCondition propCond = new PropertyCondition();
        propCond.setName(name);
        propCond.setOperator(SupportedOps.EQ);
        propCond.setVal(value);
        return propCond;
    }
}
