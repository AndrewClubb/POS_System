package posPD;
 /**
 * An abstraction of payments that must be authorized
 */
public abstract class AuthorizedPayment extends Payment
{

	/**
	 * The authorization code given
	 */
	private String authorizationCode;

	public String getAuthorizationCode()
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
	}

	/**
	 * Returns if payment was authorized
	 * @return If payment was authorized
	 */
	public Boolean isAuthorized()
	{
		// TODO - implement AuthorizedPayment.isAuthorized
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns if payment was cash
	 * @return If payment was cash
	 */
	public Boolean countsAsCash()
	{
		// TODO - implement AuthorizedPayment.countsAsCash
		throw new UnsupportedOperationException();
	}

}