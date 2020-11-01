package msgies;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JavaConsumer implements Runnable {

	@Override
	public void run() {
		try {
			//Create connection.
			Connection connection = new ActiveMQConnectionFactory(
							"tcp://localhost:61616")
							.createConnection();
			// Start the connection
			connection.start();
			// Cria a sessão
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Crea a fila e informa qual o destinatário.
			Destination queue = session.createQueue("Queue");

			MessageConsumer consumer = session.createConsumer(queue);

			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Consumer Received: " + text);
			}

			session.close();

			connection.close();
		} catch (Exception ex) {
			System.out.println("Exception Occured");
		}
	}
}