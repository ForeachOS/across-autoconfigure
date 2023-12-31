package ax.server.application;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author Arne Vandamme
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class PropertyUpdateController
{
	private final ApplicationContext applicationContext;

	@SneakyThrows
	@GetMapping("/customProperty")
	public String customProperty( @RequestParam("value") String value ) {
		Properties properties = new Properties();
		properties.setProperty( "custom.property", value );

		String tempDir = applicationContext.getEnvironment().getProperty( "java.io.tmpdir" );

		new File( tempDir, "ax-cloud-test-props" ).mkdirs();
		File file = new File( tempDir, "ax-cloud-test-props/myclient.properties" );

		try (FileOutputStream fileOut = new FileOutputStream( file )) {
			properties.store( fileOut, "Custom properties" );
		}

		return "OK";
	}

	@SneakyThrows
	@GetMapping("/renderViewElementNames")
	public String renderViewElementNames( @RequestParam("enabled") boolean enabled ) {
		Properties properties = new Properties();
		properties.setProperty( "across.web.development.renderViewElementNames", "" + enabled );

		String tempDir = applicationContext.getEnvironment().getProperty( "java.io.tmpdir" );

		new File( tempDir, "ax-cloud-test-props" ).mkdirs();
		File file = new File( tempDir, "ax-cloud-test-props/myclient-development.properties" );

		try (FileOutputStream fileOut = new FileOutputStream( file )) {
			properties.store( fileOut, "Render view element names" );
		}

		return "OK";
	}
}
