
package business;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.tools.Converter;
import javax.net.ssl.SSLException;
import java.io.IOException;


public class Messaging {
    
    
    
     public static void sendmessage(String message) throws IOException{ 
     String channelName = "transaction_channel", 
              clientID = "transaction-subscriber";
                String kubeMQAddress = System.getenv("kubeMQAddress");
         
  io.kubemq.sdk.event.Channel channel = new io.kubemq.sdk.event.Channel(channelName, clientID, false,
          kubeMQAddress);
 
     channel.setStore(true);
      Event event = new Event();
      event.setBody(Converter.ToByteArray(message));
      event.setEventId("event-Store-" );
      try {
          channel.SendEvent(event);
      } catch (SSLException e) {
          System.out.printf("SSLException: %s", e.getMessage());
          e.printStackTrace();
      } catch (ServerAddressNotSuppliedException e) {
          System.out.printf("ServerAddressNotSuppliedException: %s", e.getMessage());
          e.printStackTrace();
      }
  
     }
    
}
