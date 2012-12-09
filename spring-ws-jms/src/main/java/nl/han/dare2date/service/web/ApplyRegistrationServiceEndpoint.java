package nl.han.dare2date.service.web;

import nl.han.dare2date.applyregistrationservice.ApplyRegistrationRequest;
import nl.han.dare2date.applyregistrationservice.ApplyRegistrationResponse;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

@Endpoint
public class ApplyRegistrationServiceEndpoint {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public ApplyRegistrationServiceEndpoint(Marshaller marshaller,
			Unmarshaller unmarshaller) {
		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	@PayloadRoot(localPart = "ApplyRegistrationRequest", namespace = "http://www.han.nl/schemas/messages")
	public ApplyRegistrationResponse applyRegistration(ApplyRegistrationRequest req) {
		return new ApplyRegistrationResponse();
	}
}
