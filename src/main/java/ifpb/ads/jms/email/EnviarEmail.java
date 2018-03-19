package ifpb.ads.jms.email;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 12/03/2018, 10:17:56
 */
@JMSDestinationDefinition(
        name = "java:global/jms/Fila",
        interfaceName = "javax.jms.Queue",
        resourceAdapter = "jmsra",
        destinationName = "queue")
@Stateless
public class EnviarEmail {

    //"java:global/jms/Fila
//    @Resource(lookup = "jms/aulaQueue")
//    @Resource(lookup = "jms/dac/Queue")
    @Resource(lookup = "java:global/jms/Fila")
    private Queue fila;

    @Inject
    private JMSContext context;

    public void enviar(String email) {
        JMSProducer createProducer = context.createProducer();
        createProducer.send(fila, email);
        System.out.println("email enviado.. " + email);
    }
}
