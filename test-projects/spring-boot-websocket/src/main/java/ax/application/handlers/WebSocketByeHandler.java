package ax.application.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Steven Gentens
 */
@Component
public class WebSocketByeHandler extends TextWebSocketHandler
{
	private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void handleTextMessage( @NonNull WebSocketSession session, @NonNull TextMessage message )
			throws IOException {

		for ( WebSocketSession webSocketSession : sessions ) {
			Map<String, String> value = objectMapper.readValue( message.getPayload(), new TypeReference<Map<String, String>>()
			{
			} );
			webSocketSession.sendMessage( new TextMessage( "Bye " + value.get( "name" ) + " !" ) );
		}
	}

	@Override
	public void afterConnectionEstablished( @NonNull WebSocketSession session ) {
		//the messages will be broadcasted to all users.
		sessions.add( session );
	}
}
