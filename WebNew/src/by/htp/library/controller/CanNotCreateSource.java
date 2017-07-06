package by.htp.library.controller;

public class CanNotCreateSource extends RuntimeException {

	private static final long serialVersionUID = 9099693622276124572L;
	
	public CanNotCreateSource() {
			super();
			}
	public CanNotCreateSource(String message) {
		super(message);
		}
	public CanNotCreateSource(Exception e) {
		super(e);
		}
	public CanNotCreateSource(String message,Exception e) {
		super(message,e);
		}
	
}
