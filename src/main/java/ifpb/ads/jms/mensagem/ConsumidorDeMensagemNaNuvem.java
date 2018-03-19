package ifpb.ads.jms.mensagem;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/03/2018, 07:51:56
 */

@MessageDriven(mappedName = "java:global/jms/Topic",
        activationConfig ={
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "topic"),
            @ActivationConfigProperty(propertyName = "messageSelector",
                    propertyValue = "categoria='nuvem'")
        } )
public class ConsumidorDeMensagemNaNuvem implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);
            Logger.getLogger(ConsumidorDeMensagemNaNuvem.class.getName())
                    .log(Level.INFO, "Mensagem recebida na nuvem:{0}", body);
        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorDeMensagemNaNuvem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
