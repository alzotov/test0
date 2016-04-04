package zlab.exp.camel.boot.test0.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by alexey.zotov on 3/31/2016.
 */
public class CardTransaction{
    Card card;
    BigDecimal amount;


    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardTransaction other = (CardTransaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		return true;
	}
	
	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("card", card)
                .append("amount", amount)
                .toString();
    }
}
