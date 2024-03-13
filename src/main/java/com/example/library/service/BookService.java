package com.example.library.service;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.library.entity.BookDetails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

	//fetching the book details
 public List<String> getBooks(String userType) {
     List<String> books = new ArrayList<>();
     BufferedReader reader=null;
     try {
         
         if (userType=="admin") {
             reader = new BufferedReader(new InputStreamReader(new ClassPathResource("adminUser.csv").getInputStream()));
         } else {
             reader = new BufferedReader(new InputStreamReader(new ClassPathResource("regularUser.csv").getInputStream()));
         }
         String line;
         while ((line = reader.readLine()) != null) {
             books.add(line);
         }
         reader.close();
     } catch (IOException e) {
         e.printStackTrace();
         return new ArrayList<>();
     } finally {
         // Close the reader in a finally block to ensure it is always closed
         if (reader != null) {
             try {
                 reader.close();
             } catch (IOException e) {
                 // Log the error if closing the reader fails
                 e.printStackTrace();
             }
         }
     }
     return books;
 }
 
 // adding new book record
 public void addBook(BookDetails bookRequest) {
     if (!isValidBook(bookRequest)) {
         throw new IllegalArgumentException("Invalid book parameters");
     }

     try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(new ClassPathResource("regularUser.csv").getFile(), true));
         writer.write(String.format("%s,%s,%d%n", bookRequest.getBookName(), bookRequest.getAuthor(), bookRequest.getPublicationYear()));
         writer.close();
     } catch (IOException e) {
         e.printStackTrace();
         throw new RuntimeException("Failed to add book");
     }
 }

 	private boolean isValidBook(BookDetails bookRequest) {
 		return isStringValid(bookRequest.getBookName()) &&
 				isStringValid(bookRequest.getAuthor()) &&
 				isValidYear(bookRequest.getPublicationYear());
 	}

		 private boolean isStringValid(String str) {
		     return str != null && !str.isEmpty();
		 }
		
		 private boolean isValidYear(int year) {
		     return year >= 1000 && year <= 9999; // Assuming a valid year range
		 }
		 
		 //Implementing deletion of book record...
		 public void deleteBook(String bookName) {
		        String lowerCaseBookName = bookName.toLowerCase();
		        try {
		            File inputFile = new ClassPathResource("regularUser.csv").getFile();
		            File tempFile = new File("temp.csv");

		            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		            String lineToRemove = lowerCaseBookName + ",";
		            String currentLine;
		            while ((currentLine = reader.readLine()) != null) {
		                String trimmedLine = currentLine.trim().toLowerCase();
		                if (!trimmedLine.startsWith(lineToRemove)) {
		                    writer.write(currentLine + System.getProperty("line.separator"));
		                }
		            }
		            writer.close();
		            reader.close();
		            tempFile.renameTo(inputFile);
		        } catch (IOException e) {
		            e.printStackTrace();
		            throw new RuntimeException("Failed to delete book");
		        }
		    }
		 
		 
		
	}

