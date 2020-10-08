package ax.modules.websocket.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Steven Gentens
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HelloMessage
{
	private String name;
}
