package zlab.exp.camel.boot.test0.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import zlab.exp.camel.boot.test0.model.CardType;

/**
 * Created by alexey.zotov on 3/31/2016.
 */
public class CardTransactionRz{
    public CardTransactionRz(CardTransaction transaction) {
        this.transaction = transaction;
    }

    private CardTransaction transaction;

    private CardType cardType;

    private String code;
    private String description;


    public CardTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(CardTransaction transaction) {
        this.transaction = transaction;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
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
		CardTransactionRz other = (CardTransactionRz) obj;
		if (cardType != other.cardType)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("transaction", getTransaction())
                .append("cardType", getCardType())
                .append("code", getCode())
                .append("description", getDescription())
                .toString();
    }

}
