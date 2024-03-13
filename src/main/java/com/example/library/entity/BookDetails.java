package com.example.library.entity;

public class BookDetails {

	private String bookName;
    private String author;
    private int publicationYear;
    
    
	public BookDetails(String bookName, String author, int publicationYear) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.publicationYear = publicationYear;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	@Override
	public String toString() {
		return "BookDetails [bookName=" + bookName + ", author=" + author + ", publicationYear=" + publicationYear
				+ "]";
	}
    
    

}
