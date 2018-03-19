package ifpb.ads.jms.email;

import ifpb.ads.jms.mensagem.ConsumidorDeMensagemNaNuvem;
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
 * @since 13/03/2018, 08:32:43
 */
@MessageDriven(mappedName = "java:global/jms/Fila",
        activationConfig ={
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "queue")
        } )
public class ConsumidorDeEmail implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
            String body = message.getBody(String.class);
            Logger.getLogger(ConsumidorDeEmail.class.getName())
                    .log(Level.INFO, "Email recebido:{0}", body);
        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorDeEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
