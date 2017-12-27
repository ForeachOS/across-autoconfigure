package ax;

import ax.application.business.Customer;
import ax.application.repositories.CustomerRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootDataCassandraApplication.class)
public class TestSpringBootDataCassandraApplication
{
	@Autowired
	private CustomerRepository repository;

	@Test
	public void shouldBootstrap() throws IOException {
		assertNotNull( repository );

		repository.deleteAll();

		assertEquals( 0, repository.count() );

		Customer alice = new Customer( UUIDs.timeBased(), "Alice", "Smith" );
		Customer bob = new Customer( UUIDs.timeBased(), "Bob", "Smith" );

		repository.save( alice );
		repository.save( bob );

		assertEquals( 2, repository.count() );

		assertEquals( alice, repository.findByFirstName( "Alice" ) );
		assertEquals( 2, repository.findByLastName( "Smith" ).size() );
	}
}