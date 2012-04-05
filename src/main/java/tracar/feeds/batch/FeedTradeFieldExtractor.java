package tracar.feeds.batch;

import org.oddjob.arooa.utils.DateHelper;
import org.springframework.batch.item.file.transform.FieldExtractor;

import tracar.feeds.FeedTradeBean;

public class FeedTradeFieldExtractor 
implements FieldExtractor<FeedTradeBean>{

	@Override
	public Object[] extract(FeedTradeBean item) {
		return new Object[] {
                item.getSequenceNumber(),
                item.getTradeRef(),
                DateHelper.formatDate(item.getTradeDate()),
                item.getAccountCode(),
                item.getExchangeCode(),
                item.getProductType(),
                item.getProductCode(),
                item.getSide(),
                item.getQuantity(),
                item.getTradePrice(),
                item.getCounterpartyCode()
		};
	}
}
