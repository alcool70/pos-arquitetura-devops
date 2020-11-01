package msgies;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class JavaProducer implements Runnable {

	public void run() {
		try {

			//Create connection.
			Connection connection = new ActiveMQConnectionFactory(
							"tcp://localhost:61616").createConnection();


			// Start the connection
			connection.start();

			// Create a session which is non transactional
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create Destination queue
			Destination queue = session.createQueue("Queue");

			// Create a producer
			MessageProducer producer = session.createProducer(queue);

			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			String data = Calendar
							.getInstance(
											TimeZone.getTimeZone("GMT-03:00"),
											new Locale("pt", "BR"))
							.getTime().toString();

			String msg =
							"Aula de Java Pos Web ".concat(data);

			// insert message
			TextMessage message = session.createTextMessage(msg);
			System.out.println("Producer Sent: " + msg);
			producer.send(message);

			session.close();
			connection.close();
		} catch (Exception ex) {
			System.out.println("Exception Occured");
		}
	}
}