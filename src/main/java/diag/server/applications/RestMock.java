package diag.server.applications;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    15/05/2015 16:38
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class RestMock {

  //{"message":null,"messageKey":null,"success":true,"timeFrame":null,"applications":[{"tenantId":123456,"appExternalId":107,"applicationKey":"jm94gebira"}]}
  //{"message":null,"messageKey":null,"success":true,"timeFrame":null,"applications":[{"tenantId":123456,"appExternalId":107,"applicationKey":"jm94gebira"},{"tenantId":11,"appExternalId":56,"applicationKey":"test11"}]}
  //{"message":null,"messageKey":null,"success":true,"timeFrame":null,"applications":[{"tenantId":123456,"appExternalId":107,"applicationKey":"jm94gebira"},{"tenantId":11,"appExternalId":56,"applicationKey":"test11"}],"probeTokens":[{"tenantId":123456,"token":"T123456"},{"tenantId":11,"token":"T11"}]}



  private static int counter = 0;
  private static String[] results = new String[] {
    "{\"message\":null,\"messageKey\":null,\"success\":true,\"timeFrame\":null,\"applications\":[{\"tenantId\":123456,\"appExternalId\":107,\"applicationKey\":\"jm94gebira_1\"}]}",
    "{\"message\":null,\"messageKey\":null,\"success\":true,\"timeFrame\":null,\"applications\":[{\"tenantId\":123456,\"appExternalId\":107,\"applicationKey\":\"jm94gebira_1\"},{\"tenantId\":11,\"appExternalId\":56,\"applicationKey\":\"test11\"}]}",
    "{\"message\":null,\"messageKey\":null,\"success\":true,\"timeFrame\":null,\"applications\":[{\"tenantId\":123456,\"appExternalId\":107,\"applicationKey\":\"jm94gebira_2\"},{\"tenantId\":11,\"appExternalId\":56,\"applicationKey\":\"test11\"}]}",
    "{\"message\":null,\"messageKey\":null,\"success\":true,\"timeFrame\":null,\"applications\":[{\"tenantId\":123456,\"appExternalId\":107,\"applicationKey\":\"jm94gebira_2\"},{\"tenantId\":11,\"appExternalId\":56,\"applicationKey\":\"test11\"}],\"probeTokens\":[{\"tenantId\":123456,\"token\":\"T123456_1\"},{\"tenantId\":11,\"token\":\"T11\"}]}",
    "{\"message\":null,\"messageKey\":null,\"success\":true,\"timeFrame\":null,\"applications\":[{\"tenantId\":123456,\"appExternalId\":107,\"applicationKey\":\"jm94gebira_2\"},{\"tenantId\":11,\"appExternalId\":56,\"applicationKey\":\"test11\"}],\"probeTokens\":[{\"tenantId\":123456,\"token\":\"T123456_2\"},{\"tenantId\":11,\"token\":\"T11\"}]}"
  };

  public static JsonObject restCall2Purple() {
    JsonReader jsonReader = Json.createReader(new StringReader(results[counter++ % results.length]));
    JsonObject result = jsonReader.readObject();
    jsonReader.close();

    return result;
  }
}
