package ifpb.ads.jms.mensagem;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/03/2018, 07:24:42
 */
@JMSDestinationDefinition(
        name = "java:global/jms/Topic",
        interfaceName = "javax.jms.Topic",
        resourceAdapter = "jmsra",
        destinationName = "topic")
@Stateless
public class EnviarMensagem {

    @Resource(lookup = "java:global/jms/Topic")
    private Topic topic;
    @Inject
    private JMSContext context;

    public void enviar(String categoria, String mensagem) {
//    public void enviar(String mensagem) {
        JMSProducer createProducer = context.createProducer();
//        for (int i = 0; i < 100; i++) {
//            String enviar = mensagem + " - " + i;
        String enviar = mensagem;
        createProducer.setProperty("categoria", categoria)
                .send(topic, enviar);
        Logger.getGlobal().log(Level.INFO, "Mensagem enviada:{0}", enviar);
//        }
    }
}
