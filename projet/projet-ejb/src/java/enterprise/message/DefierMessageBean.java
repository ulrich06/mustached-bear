/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author momo
 */
@MessageDriven(mappedName = "jms/defierMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class DefierMessageBean implements MessageListener {
    
    public DefierMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage)
        {
            TextMessage tm = (TextMessage) message;  
            try {  
                String text = tm.getText();  
                System.out.println("Received new message :" + text);  
            } catch (JMSException e) {  
            }  
        }
    }
}
