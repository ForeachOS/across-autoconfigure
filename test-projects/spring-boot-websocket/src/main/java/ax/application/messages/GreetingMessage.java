package ax.application.messages;

/**
 * @author Steven Gentens
 */
public class GreetingMessage
{
	private String content;

	public GreetingMessage() {
	}

	public GreetingMessage( String content ) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}