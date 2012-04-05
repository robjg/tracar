package tracar.feeds.batch;

import java.text.ParseException;

import org.oddjob.arooa.utils.DateHelper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import tracar.feeds.FeedTradeBean;

public class FeedTradeFieldSetMapper 
implements FieldSetMapper<FeedTradeBean>{

	@Override
	public FeedTradeBean mapFieldSet(FieldSet fieldSet) throws BindException {
		FeedTradeBean feedTrade = new FeedTradeBean();
		
		feedTrade.setSequenceNumber(fieldSet.readString("sequenceNumber"));
		feedTrade.setTradeRef(fieldSet.readString("tradeRef"));
		try {
			feedTrade.setTradeDate(DateHelper.parseDate(
					fieldSet.readString("tradeDate")));
		} catch (ParseException e) {
			throw new BindException(fieldSet.readString("tradeDate"), "tradeDate");
		}
		feedTrade.setAccountCode(fieldSet.readString("accountCode"));
		feedTrade.setExchangeCode(fieldSet.readString("exchangeCode"));
		feedTrade.setProductType(fieldSet.readString("productType"));
		feedTrade.setProductCode(fieldSet.readString("productCode"));
		feedTrade.setSide(fieldSet.readString("side"));
		feedTrade.setQuantity(fieldSet.readInt("quantity"));
		feedTrade.setTradePrice(fieldSet.readDouble("tradePrice"));
		feedTrade.setCounterpartyCode(fieldSet.readString("counterpartyCode"));

		return feedTrade;
	}
}
