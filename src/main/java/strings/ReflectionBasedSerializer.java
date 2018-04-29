package strings;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    10/01/2011 17:21:50
 * <B>Since:</B>       BSM 9.1
 * <B>Description:</B>
 * Prints a given object to XML
 * It investigate the object using reflection and the result is a deep object serialization as XML.
 * <b>What is deep?</b>   
 * If the given object "a" has a field of type "B" then if (depth>=1) we recursively serialize "B" as well printing all its fields.
 * If "B" has field of type "C" and (depth>=2) we serialize it as well.
 * The depth is how deep we serialize recursively.
 * </pre>
 */
public class ReflectionBasedSerializer {

    public static String toXmlString(Object o) throws IllegalAccessException {
        return toXmlString(o, 0, false);
    }

    public static String toXmlString(Object o, int depth, boolean includingStaticFields) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        try {
            toXmlString(o, sb, depth, includingStaticFields);
        }
        catch (IllegalAccessException e) {
            throw e;
        }
        catch (Throwable t) {
            System.err.println("Failed to serialize. Partial: " + sb.toString());
            t.printStackTrace(System.err);
            
        }
        return sb.toString();
    }

    private static String valueToString(Object obj) {
        final String strValue = obj.toString();
        boolean containsInvalidXmlCharacters = (strValue.contains("<")) || (strValue.contains(">")) || (strValue.contains("\"")) || (strValue.contains("\'")) || (strValue.contains("&"));
        if (containsInvalidXmlCharacters) {
            return "<![CDATA[" + strValue.trim().replaceAll(">\\s*<", "><") + "]]>";
        }
        else {
            return strValue;
        }
    }


    private static void toXmlString(Object o, StringBuilder sb, int depth, boolean includingStaticFields) throws IllegalAccessException {
        if (o==null) {
            sb.append("null");
            return;
        }

        if (isPrimitive(o)) {
            sb.append(valueToString(o));
            return;
        }

        if (depth==0) {
            sb.append(valueToString(o));
            return;
        }


        if (o instanceof Object[]) {
            Object[] array = (Object[]) o;
            sb.append("<Array size=\"").append(array.length).append("\">");
            for (Object item : array) {
                toXmlString(item, sb, depth-1, includingStaticFields);
            }
            sb.append("</Array>");
            return;
        }

        Class clazz = o.getClass();
        Field f[] = getFieldsFromClass(clazz, includingStaticFields);

        sb.append("<" + clazz.getSimpleName() + " depth=\"");
        sb.append(depth);
        sb.append("\" ");
        if (includingStaticFields) {
            sb.append("type=\"");
            sb.append(clazz.getSimpleName());
            sb.append("\" ");
            sb.append("parents=\"");
            sb.append(calculateParents(clazz));
            sb.append("\"");
        }
        sb.append(">");
        sb.append("<Fields>");
        for (Field aF : f) {
            if (aF!=null) {
                aF.setAccessible(true);
                final String fieldName = aF.getName();
                final Object fieldValue = aF.get(o);
                if (!"serialVersionUID".equals(fieldName) && fieldValue!=o) {
                    sb.append("<Field>");
                    sb.append("<Name>").append(fieldName).append("</Name>");
                    if (fieldValue !=null && depth>0) {
                        sb.append("<Value>");
                        toXmlString(fieldValue, sb, depth-1, includingStaticFields);
                        sb.append("</Value>");
                    }
                    else {
                        if (isPrimitive(o)) {
                            sb.append(valueToString(o));
                        }
                        else {
                            //sb.append("<Value><![CDATA[").append(fieldValue).append("]]></Value>");
                            sb.append("<Value>").append(fieldValue).append("</Value>");
                        }
                    }

                    sb.append("</Field>");
                }
            }
        }
        sb.append("</Fields>");

        sb.append("</"+clazz.getSimpleName()+">");
    }

    private static Field[] getFieldsFromClass(Class clazz, boolean includingStaticFields) {
        if (clazz==null) return new Field[0];

        final Field[] fieldsFromParent=getFieldsFromClass(clazz.getSuperclass(), includingStaticFields);

        final Field[] fieldsFromThis=clazz.getDeclaredFields();
        Field[] res = new Field[fieldsFromParent.length+fieldsFromThis.length];
        int index = 0;
        for (Field field : fieldsFromParent) {
            res[index++] = field;
        }

        for (Field field : fieldsFromThis) {
            if (includingStaticFields || !Modifier.isStatic(field.getModifiers())) {
                res[index++] = field;
            }
        }
        return res;
    }

    private static String calculateParents(Class clazz) {
        final StringBuilder result = new StringBuilder();
        addParentsToStringBuilder(result, clazz);
        return result.toString();
    }


    private static void addParentsToStringBuilder(StringBuilder sb, Class clazz) {
        if (clazz==null) return;
        final Class parent=clazz.getSuperclass();
        if (parent==null) return;
        sb.append(parent.getSimpleName()).append(",");
        addParentsToStringBuilder(sb, parent);

    }

    private static boolean isPrimitive(Object o) {
        return
            o instanceof Boolean |
                o instanceof Integer |
                o instanceof Long |
                o instanceof Float |
                o instanceof Double |
                o instanceof String
            ;
    }
}
