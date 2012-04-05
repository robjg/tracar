package tracar.services;

import tracar.domain.Product;

public interface InstrumentService {

	Product instrumentFor(String securityCode);
}
