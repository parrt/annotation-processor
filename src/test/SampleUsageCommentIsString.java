package test;

public class SampleUsageCommentIsString {
	/** foo */
	@CommentIsString
	static String x;

	/** foo */
	@CommentIsString
	static String getX() {return null;}

	public static void main(String[] args) {
		System.out.println(x);
		System.out.println(getX());
	}
}
