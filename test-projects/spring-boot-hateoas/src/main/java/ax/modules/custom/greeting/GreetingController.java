package ax.modules.custom.greeting;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * From: https://spring.io/guides/gs/rest-hateoas/
 * Because Greeting is returned as a HTTP entity, regular JSON response type will generate different output.
 *
 * @author Arne Vandamme
 * @since 1.0.2
 */
@RestController
public class GreetingController
{
	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping("/greeting")
	public HttpEntity<Greeting> greeting( @RequestParam(value = "name", required = false, defaultValue = "World") String name ) {
		Greeting greeting = new Greeting( String.format( TEMPLATE, name ) );
		greeting.add( linkTo( methodOn( GreetingController.class ).greeting( name ) ).withSelfRel() );

		return new ResponseEntity<>( greeting, HttpStatus.OK );
	}
}
