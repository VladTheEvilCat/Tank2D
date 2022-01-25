package utils.exceptions;

@SuppressWarnings("serial")
public class ReloadFinished extends RuntimeException {
	public ReloadFinished(String s) {
		super(s);
	}
}
