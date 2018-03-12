package ifpb.ads.jms.email;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 12/03/2018, 10:17:56
 */
@Stateless
public class LerEmail {

    @Resource(lookup = "jms/aulaQueue")
//    @Resource(lookup = "jms/dac/Queue")
//    @Resource(lookup = "java:global/jms/Fila")
    private Queue fila;

    @Inject
    private JMSContext context;

    public String ler() {
        JMSConsumer createConsumer = context.createConsumer(fila);
        TextMessage receive = (TextMessage) createConsumer.receive();
        try {
//            Logger.getLogger(LerEmail.class.getName()).log(Level.INFO, "Lendo o email");
            return fila.getQueueName() + " - " + receive.getText();
//        String receiveBody = createConsumer.receiveBody(String.class);
//        return receiveBody;
        } catch (JMSException ex) {
            Logger.getLogger(LerEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        context.createBrowser(fila).getEnumeration();
        return "sem email";
    }
}
