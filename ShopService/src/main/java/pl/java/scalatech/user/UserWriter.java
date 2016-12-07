package pl.java.scalatech.user;

import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import pl.java.scalatech.user.domain.User;

@MessagingGateway
@Profile("writer")
interface UserWriter {

	@Gateway(requestChannel = "output")
	void writeUser(User user);
}